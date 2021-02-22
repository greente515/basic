package com.example.basic.local.hello.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "test", description = "tset controller")
@RestController
public class TestController {

    @Operation(tags = {"test1"}, summary = "hello string test")
    @GetMapping("/hello/string")
    public String hello1(){
        return "helloWorld";
    }

}
