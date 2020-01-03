package com.decoders.leaves.controller;


import com.decoders.leaves.config.MessageBody;
import com.decoders.leaves.entities.SystemUser;
import com.decoders.leaves.exception.ResourceException;
import com.decoders.leaves.resource.SystemUserResource;
import com.decoders.leaves.security.JwtTokenProvider;
import com.decoders.leaves.security.LoginUser;
import com.decoders.leaves.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/systemUser")
public class SystemUserController {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private SystemUserService systemUserService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<MessageBody> loginSystemUser(@RequestBody SystemUserResource systemUserResource) {

        if(systemUserResource.getUsername() == null
                || systemUserResource.getUsername().isEmpty()
                || systemUserResource.getPassword() == null
                || systemUserResource.getPassword().isEmpty()){
            throw new ResourceException(HttpStatus.BAD_REQUEST, "invalid_request");
        }

        SystemUser systemUser = systemUserService.login(systemUserResource.toSystemUser());

        LoginUser loginUser = new LoginUser(systemUser);
        String token = jwtTokenProvider.createToken(loginUser);

        systemUserResource = SystemUserResource.toResource(systemUser);
        systemUserResource.setToken(token);

        MessageBody messageBody = MessageBody.getInstance();

        messageBody.setStatus("200");
        messageBody.setText("OK");
        messageBody.setBody(systemUserResource);

        return new ResponseEntity<>(messageBody, HttpStatus.OK);
    }
}
