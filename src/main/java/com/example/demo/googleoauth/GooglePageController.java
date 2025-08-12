package com.example.demo.googleoauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GooglePageController {
    @GetMapping("/google")
    public String google() { return "google"; }
}