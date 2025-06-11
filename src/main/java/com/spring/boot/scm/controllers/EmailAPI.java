package com.spring.boot.scm.controllers;

import com.spring.boot.scm.models.EmailRequest;
import com.spring.boot.scm.models.EmailResponse;
import com.spring.boot.scm.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailAPI {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome() {
        return "Welcome to SmartContactManager";
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest email) {
        boolean result = this.emailService.sendEmail(email.getTo(), email.getSubject(), email.getMessage());
        //return ResponseEntity.ok().build();
        System.out.println(email);
        if (result) {
            return ResponseEntity.ok(new EmailResponse("Email sent successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Internal Server Error"));
        }
    }
}
