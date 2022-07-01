package com.example.hitest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/index")
    public String index() {
        log.info("### index.Controller start");
        return "/index";
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "redirect";
    }

}
