package com.decoders.leaves.controller;


import com.decoders.leaves.config.MessageBody;
import com.decoders.leaves.entities.LeaveTrack;
import com.decoders.leaves.entities.LeaveType;
import com.decoders.leaves.entities.SystemUser;
import com.decoders.leaves.exception.ResourceException;
import com.decoders.leaves.resource.LeaveTrackResource;
import com.decoders.leaves.resource.LeaveTypeResource;
import com.decoders.leaves.security.JwtTokenProvider;
import com.decoders.leaves.security.LoginUser;
import com.decoders.leaves.service.LeaveService;
import com.decoders.leaves.service.LeaveTrackService;
import com.decoders.leaves.service.LeaveTypeService;
import com.decoders.leaves.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/leaveType")
public class LeaveTypeController {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private LeaveTypeService leaveTypeService;

    @Autowired
    private SystemUserService systemUserService;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<MessageBody> createLeaveTrack(HttpServletRequest request) {

        String token = jwtTokenProvider.resolveToken(request);

        LoginUser loginUser = jwtTokenProvider.getLoginUser(token);

        SystemUser systemUser = systemUserService.findByUsername(loginUser.getUsername());

        List<LeaveType> leaveTypeList = leaveTypeService.findAll();

        List<LeaveTypeResource> leaveTypeResourceList = LeaveTypeResource.toResource(leaveTypeList);

        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setStatus("200");
        messageBody.setText("OK");
        messageBody.setBody(leaveTypeResourceList);

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }
}
