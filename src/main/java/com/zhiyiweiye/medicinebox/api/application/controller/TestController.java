package com.zhiyiweiye.medicinebox.api.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
