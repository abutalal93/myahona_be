package com.decoders.leaves.scheduler;

import com.decoders.leaves.Utils.Utils;
import com.decoders.leaves.entities.Leave;
import com.decoders.leaves.entities.LeaveTrack;
import com.decoders.leaves.service.LeaveService;
import com.decoders.leaves.service.LeaveTrackService;
import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableScheduling
public class LeaveScheduler {

    @Autowired
    private LeaveService leaveService;

    @Scheduled(cron = "0 0 6 * * *")/*@Scheduled(cron = "0 0 6 * * *") @Scheduled(fixedDelay = 1000)*/
    public void scheduleFixedDelayTask() {
        try {
            System.out.println("M --->");

            List<Leave> leaveList = leaveService.findNonCompleatedLeave();

            if(leaveList == null || leaveList.isEmpty()){
                System.out.println("leaveList is empty");
                return;
            }

            String htmlEmailBody = "<h2>Dear HR Team,</h2>" +
                    "<p>Kindly refer to the following non completed leaves.</p>" +
                    "<table style=\'width:100%;border: 3px solid black;border-collapse: collapse;\'>" +
                    "  <tr>" +
                    "    <th style=\'text-align: left;\'>Employee Number</th>" +
                    "    <th style=\'text-align: left;\'>Leave Number</th>" +
                    "    <th style=\'text-align: left;\'>Status</th>" +
                    "  </tr>" +
                        "****"+
                    "</table>";

            String leaveRow = "";
            for(Leave leave: leaveList){

                leaveRow += "<tr>" +
                        "    <td>"+leave.getEmployeeNumber()+"</td>" +
                        "    <td>"+leave.getNumber()+"</td>" +
                        "    <td>"+leave.getStatus().getDescription()+"</td>" +
                        "  </tr>";

            }

            htmlEmailBody = htmlEmailBody.replace("****",leaveRow);

            System.out.println("htmlEmailBody: "+htmlEmailBody);

            String jsonMessageBody = Utils.prepareEmailJsonMessage("abdallah.dabbas.93@gmail.com" , "Leave Tracker", "abdallah.dabbas.93@hotmail.com" , "HR", "Leaves Follow up", htmlEmailBody);

            System.out.println("jsonMessageBody: "+jsonMessageBody);

            OkHttpClient client = new OkHttpClient();

            JSONObject requestJson = new JSONObject();

            requestJson.put("","");

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonMessageBody);
            Request request = new Request.Builder()
                    .url("https://api.mailjet.com/v3.1/send")
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Basic MWY1MTE0NjI1NDNmMjIwZjFkNmU3YzI0MDg3NmZkZTI6MTlkOTUwZjZiMGI5MDY4M2I5M2IxOWExZjU2ZmJmNTM=")
                    .addHeader("cache-control", "no-cache")
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject responseJson = new JSONObject(response.body().string());
            System.out.println(responseJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
