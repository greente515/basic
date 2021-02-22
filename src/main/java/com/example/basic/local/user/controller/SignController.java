package com.example.basic.local.user.controller;

import com.example.basic.global.advice.exception.CustomEmailSigninFailedException;
import com.example.basic.global.common.response.model.CommonResponse;
import com.example.basic.global.common.response.model.SingleResponse;
import com.example.basic.global.common.response.service.ResponseService;
import com.example.basic.global.security.JwtTokenProvider;
import com.example.basic.local.user.entity.User;
import com.example.basic.local.user.repository.UserJpaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@Tag(name = "sign", description = "the sign API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/sign")
public class SignController {

    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;


    @Operation(summary = "이메일 회원 로그인을 한다", tags = { "로그인" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid username/password supplied") })
    @PostMapping(value = "/signin")
    public SingleResponse<String> signin(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Created user object", required = true)
            @Parameter(name = "회원ID", description = "이메일로된 회원의 ID", in = ParameterIn.PATH)
            @RequestParam String id,
            @RequestParam String password
    ){
        User user = userJpaRepository.findByUid(id).orElseThrow(CustomEmailSigninFailedException::new);
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new CustomEmailSigninFailedException();
        }
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }

    @PostMapping("/signup")
    public CommonResponse signup(
            @RequestParam String id,
            @RequestParam String password,
            @RequestParam String name
    ){
        userJpaRepository.save(
                User.builder()
                .uid(id)
                .name(name)
                .password(passwordEncoder.encode(password))
                .roles(Collections.singletonList("ROLE_USER"))
                .build()
        );
        return responseService.getSuccessResult();
    }

}
