package com.spring.boot.scm.controllers;

import com.spring.boot.scm.helper.Message;
import com.spring.boot.scm.models.Contact;
import com.spring.boot.scm.models.User;
import com.spring.boot.scm.repositories.ContactRepository;
import com.spring.boot.scm.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @ModelAttribute
    public void addCommonData(Model model, Principal principal) {
        String username = principal.getName();
        System.out.println("Logged User: " + username);

        User user = userRepository.getUserByUsername(username);

        model.addAttribute("user", user);
    }

    //Dashboard Home
    @RequestMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("title", "Dashboard - Smart Contact Manager");

        return "user/dashboard";
    }

    //Add contact handler
    @GetMapping("/add-contact")
    public String addContact(Model model, Principal principal) {
        model.addAttribute("title", "Add Contact");
        model.addAttribute("contact", new Contact());
        return "user/add-contact";
    }

    //Add contact process handler
    @PostMapping("/process-contact")
    public String processContact(@ModelAttribute("contact") Contact contact,
                                 @RequestParam("profileImage") MultipartFile file,
                                 Model model,
                                 Principal principal,
                                 HttpSession session) {

        if (file != null && !file.isEmpty()) {
            try{
                String fileName = file.getOriginalFilename();
                contact.setImage(fileName);


                // Save file to disk (you can change path)
                File saveFile = new ClassPathResource("static/images/").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                String username = principal.getName();
                User user = userRepository.getUserByUsername(username);
                contact.setUser(user);
                user.getContacts().add(contact);
                this.userRepository.save(user);

                System.out.println("Added to database!!!");

                /* Success message */
                session.setAttribute("message", new Message("Your contact added successfully!", "success"));
            } catch (IOException e) {
                e.printStackTrace();
                /* Error message */
                session.setAttribute("message", new Message("Something went wrong!", "error"));
            }
        } else {
            contact.setImage("default.png");
            /* Error message */
            session.setAttribute("message", new Message("Something went wrong!", "danger"));
        }

        return "user/add-contact";
    }

    //View contact handler
    @GetMapping("/view-contact/{page}")
    public String viewContact(@PathVariable("page") Integer page, Model model, Principal principal) {
        model.addAttribute("title", "View Contacts");

        String username = principal.getName();
        User user = userRepository.getUserByUsername(username);
        Pageable pageable = PageRequest.of(page, 3);
        Page<Contact> contacts = this.contactRepository.findContactByUser(user.getId(), pageable);
        model.addAttribute("contacts", contacts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contacts.getTotalPages());
        return "user/view-contact";
    }

    //Show particular contact
    @RequestMapping("/view-content/{cId}")
    public String viewContact(@PathVariable("cId") int cId,
                              Model model) {
        model.addAttribute("title", "Contact");

        Optional<Contact> contacts = this.contactRepository.findById(cId);
        Contact contact = new Contact();

        if (contacts.isPresent()) {
            contact = contacts.get();
        }

        model.addAttribute("contact", contact);

        return "user/contact-details";
    }

    @GetMapping("/delete/{cId}")
    public String deleteContact(@PathVariable("cId") int cId, Model model, HttpSession session) {
        Optional<Contact> contactOptional = this.contactRepository.findById(cId);
        Contact contact = new Contact();
        if (contactOptional.isPresent()) {
            contact = contactOptional.get();
        }

        contact.setUser(null);

        this.contactRepository.delete(contact);

        session.setAttribute("message", new Message("Your contact deleted successfully!", "success"));

        return "redirect:/user/view-contact/0";
    }

}
