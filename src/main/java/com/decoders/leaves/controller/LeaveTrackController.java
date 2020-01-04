package com.decoders.leaves.controller;


import com.decoders.leaves.config.MessageBody;
import com.decoders.leaves.entities.Leave;
import com.decoders.leaves.entities.LeaveTrack;
import com.decoders.leaves.entities.SystemUser;
import com.decoders.leaves.exception.ResourceException;
import com.decoders.leaves.resource.LeaveResource;
import com.decoders.leaves.resource.LeaveTrackResource;
import com.decoders.leaves.resource.PageResource;
import com.decoders.leaves.security.JwtTokenProvider;
import com.decoders.leaves.security.LoginUser;
import com.decoders.leaves.service.LeaveService;
import com.decoders.leaves.service.LeaveTrackService;
import com.decoders.leaves.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/leaveTrack")
public class LeaveTrackController {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LeaveTrackService leaveTrackService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> createLeaveTrack(HttpServletRequest request ,
                                                   @RequestBody LeaveTrackResource leaveTrackResource) {

        if(leaveTrackResource.getLeaveId() == null
                || leaveTrackResource.getLeaveId() == 0
                || leaveTrackResource.getStatusId() == null
                || leaveTrackResource.getStatusId() == 0){
            throw new ResourceException(HttpStatus.BAD_REQUEST, "invalid_request");
        }

        String token = jwtTokenProvider.resolveToken(request);

        LoginUser loginUser = jwtTokenProvider.getLoginUser(token);

        SystemUser systemUser = systemUserService.findByUsername(loginUser.getUsername());

        LeaveTrack leaveTrack = leaveTrackResource.toLeaveTrack();

        leaveTrack = leaveTrackService.save(leaveTrack,systemUser);

        LeaveTrackResource newLeaveTrackResource = LeaveTrackResource.toResource(leaveTrack);

        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setStatus("200");
        messageBody.setText("OK");
        messageBody.setBody(newLeaveTrackResource);

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }
}
