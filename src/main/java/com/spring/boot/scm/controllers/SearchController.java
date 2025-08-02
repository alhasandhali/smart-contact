package com.spring.boot.scm.controllers;

import com.spring.boot.scm.models.Contact;
import com.spring.boot.scm.models.User;
import com.spring.boot.scm.repositories.ContactRepository;
import com.spring.boot.scm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

//    Search Handler
    @GetMapping("/search/{query}")
    public ResponseEntity<?> searchByName(@PathVariable("query") String query, Principal principal) {
        System.out.println(query);

        User user = this.userRepository.getUserByUsername(principal.getName());

        List<Contact> contactList = this.contactRepository.findByNameContainingAndUser(query, user);

        return ResponseEntity.ok(contactList);
    }
}
