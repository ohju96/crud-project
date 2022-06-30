package com.example.crudtest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    // 인덱스 페이지
    @GetMapping("/index")
    public String index() {
        log.info("index start");
        return "/index";
    }

    // 리다이렉트 페이지
    @GetMapping("/redirect")
    public String redirect() {
        return "redirect";
    }
}
