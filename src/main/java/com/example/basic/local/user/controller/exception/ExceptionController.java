package com.example.basic.local.user.controller.exception;

import com.example.basic.global.advice.exception.CustomAuthenticationEntryPointException;
import com.example.basic.global.common.response.model.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.AccessDeniedException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public CommonResponse entrypointException(){
        throw new CustomAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public CommonResponse accessdeniedException() {
        throw new AccessDeniedException("");
    }
}
