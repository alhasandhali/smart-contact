package com.spring.boot.scm.controllers;

import com.spring.boot.scm.helper.Message;
import com.spring.boot.scm.models.User;
import com.spring.boot.scm.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping({"/", "/home"})
    public String home(Model model){
        model.addAttribute("title", "Home - Smart Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }

    @RequestMapping("/signup")
    public String signUp(Model model, HttpSession session){
        model.addAttribute("title", "Sign Up - Smart Contact Manager");
        model.addAttribute("user", new User());

        // If there's a message in session, show it
        Message message = (Message) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message"); // remove after showing once
        }

        return "signup";
    }

    //Form handler
    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("user") User user,
                              BindingResult result,
                              @RequestParam(value = "agreed", defaultValue = "false") boolean agreed,
                              Model model,
                              HttpSession session)
    {
        if (result.hasErrors()){
            System.out.println(result);
            model.addAttribute("user", user);
            return "signup";
        }
        try {
            if(!agreed){
                System.out.println("Not Agreed");
                throw new Exception("Not Agreed");
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.jpg");
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            System.out.println("Agreed -> " + agreed);
            System.out.println("User -> " + user);

            User ur = userRepository.save(user);

            System.out.println(ur);

            session.setAttribute("message", new Message("Added Successfully!!!", "alert-success"));
            model.addAttribute("user", new User());
            return "signup";

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong!!!", "alert-danger"));
            model.addAttribute("user", new User());
            return "signup";
        }
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Login - Smart Contact Manager");
        return "login";
    }
}
