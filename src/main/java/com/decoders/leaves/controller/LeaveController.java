package com.decoders.leaves.controller;


import com.decoders.leaves.config.MessageBody;
import com.decoders.leaves.entities.Leave;
import com.decoders.leaves.entities.SystemUser;
import com.decoders.leaves.exception.ResourceException;
import com.decoders.leaves.resource.LeaveResource;
import com.decoders.leaves.resource.PageResource;
import com.decoders.leaves.resource.SystemUserResource;
import com.decoders.leaves.security.JwtTokenProvider;
import com.decoders.leaves.security.LoginUser;
import com.decoders.leaves.service.LeaveService;
import com.decoders.leaves.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/leave")
public class LeaveController {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private LeaveService leaveService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> createLeave(HttpServletRequest request ,
                                                   @RequestBody LeaveResource leaveResource) {

        if(leaveResource.getEmployeeNumber() == null
                || leaveResource.getEmployeeNumber().isEmpty()
                || leaveResource.getLeaveTypeId() == null
                || leaveResource.getLeaveTypeId() == 0
                || leaveResource.getReceiveDate() == null
                || leaveResource.getStartDate() == null
                || leaveResource.getEndDate() == null
                || leaveResource.getNumberOfDays() == null
                || leaveResource.getNumberOfDays() == 0
                || leaveResource.getLeaveTrackResourceList() == null
                || leaveResource.getLeaveTrackResourceList().isEmpty()){
            throw new ResourceException(HttpStatus.BAD_REQUEST, "invalid_request");
        }

        String token = jwtTokenProvider.resolveToken(request);

        LoginUser loginUser = jwtTokenProvider.getLoginUser(token);

        SystemUser systemUser = systemUserService.findByUsername(loginUser.getUsername());

        Leave leave = leaveResource.toLeave();

        leave = leaveService.save(leave,systemUser);

        LeaveResource createLeaveResource = LeaveResource.toResource(leave);

        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setStatus("200");
        messageBody.setText("OK");
        messageBody.setBody(createLeaveResource);

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<MessageBody> updateLeave(HttpServletRequest request ,
                                                   @RequestBody LeaveResource leaveResource) {

        if(leaveResource.getId() == null
                || leaveResource.getId() == 0
                || leaveResource.getEmployeeNumber() == null
                || leaveResource.getEmployeeNumber().isEmpty()
                || leaveResource.getLeaveTypeId() == null
                || leaveResource.getLeaveTypeId() == 0
                || leaveResource.getReceiveDate() == null
                || leaveResource.getStartDate() == null
                || leaveResource.getEndDate() == null
                || leaveResource.getNumberOfDays() == null
                || leaveResource.getNumberOfDays() == 0
                || leaveResource.getLeaveTrackResourceList() == null
                || leaveResource.getLeaveTrackResourceList().isEmpty()){
            throw new ResourceException(HttpStatus.BAD_REQUEST, "invalid_request");
        }

        String token = jwtTokenProvider.resolveToken(request);

        LoginUser loginUser = jwtTokenProvider.getLoginUser(token);

        SystemUser systemUser = systemUserService.findByUsername(loginUser.getUsername());

        Leave leave = leaveResource.toLeave();

        leave = leaveService.update(leave,systemUser);

        LeaveResource createLeaveResource = LeaveResource.toResource(leave);

        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setStatus("200");
        messageBody.setText("OK");
        messageBody.setBody(createLeaveResource);

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<MessageBody> delete(HttpServletRequest request ,
                                              @RequestBody LeaveResource leaveResource) {

        if(leaveResource.getId() == null
                || leaveResource.getId() == 0){
            throw new ResourceException(HttpStatus.BAD_REQUEST, "invalid_request");
        }

        String token = jwtTokenProvider.resolveToken(request);

        LoginUser loginUser = jwtTokenProvider.getLoginUser(token);

        SystemUser systemUser = systemUserService.findByUsername(loginUser.getUsername());

        Leave leave = leaveResource.toLeave();

        leaveService.delete(leave);

        LeaveResource createLeaveResource = LeaveResource.toResource(leave);

        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setStatus("200");
        messageBody.setText("OK");
        messageBody.setBody(createLeaveResource);

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<MessageBody> findAllLeaves(HttpServletRequest request,
                                                      @RequestParam(name = "id", required = false) Long id,
                                                      @RequestParam(name = "number", required = false) String number,
                                                      @RequestParam(name = "employeeNumber", required = false) String employeeNumber,
                                                      @RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "size", required = false) Integer size) {

        String token = jwtTokenProvider.resolveToken(request);

        LoginUser loginUser = jwtTokenProvider.getLoginUser(token);

        SystemUser systemUser = systemUserService.findByUsername(loginUser.getUsername());


        Leave leaveSearchCriteria = new Leave();
        leaveSearchCriteria.setId(id);
        leaveSearchCriteria.setNumber(number);
        leaveSearchCriteria.setEmployeeNumber(employeeNumber);

        Page<Leave> leavePage = leaveService.findAll(leaveSearchCriteria, page, size);

        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setStatus("200");
        messageBody.setText("OK");
        messageBody.setBody(new PageResource(leavePage.getTotalElements(), leavePage.getTotalPages(), LeaveResource.toResource(leavePage.getContent())));

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }

}
