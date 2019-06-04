package com.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @GetMapping
    public String home(Model model) {
        logger.debug("Forward to Angular index.html");
        return "forward:/index.html";
    }
}
