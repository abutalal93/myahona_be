package com.decoders.leaves.controller;


import com.decoders.leaves.config.MessageBody;
import com.decoders.leaves.entities.Status;
import com.decoders.leaves.entities.SystemUser;
import com.decoders.leaves.resource.StatusResource;
import com.decoders.leaves.security.JwtTokenProvider;
import com.decoders.leaves.security.LoginUser;
import com.decoders.leaves.service.StatusService;
import com.decoders.leaves.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private StatusService statusService;

    @Autowired
    private SystemUserService systemUserService;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<MessageBody> createLeaveTrack(HttpServletRequest request,
                                                        @RequestParam(name = "code", required = false) String code) {

        String token = jwtTokenProvider.resolveToken(request);

        LoginUser loginUser = jwtTokenProvider.getLoginUser(token);

        SystemUser systemUser = systemUserService.findByUsername(loginUser.getUsername());

        List<Status> statusList = statusService.findStatus(code);

        List<StatusResource> statusResourceList = StatusResource.toResource(statusList);

        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setStatus("200");
        messageBody.setText("OK");
        messageBody.setBody(statusResourceList);

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }
}
