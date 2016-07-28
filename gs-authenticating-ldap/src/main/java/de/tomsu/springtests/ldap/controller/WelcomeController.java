package de.tomsu.springtests.ldap.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the controller for my Welcome message.
 * Created by Daniel on 28.07.2016.
 */
@RestController
public class WelcomeController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to my LDAP app.";
    }
}
