package com.example.basic.local.user.controller;

import com.example.basic.global.advice.exception.CustomUserExistException;
import com.example.basic.global.advice.exception.CustomUserNotFoundException;
import com.example.basic.global.common.response.model.CommonResponse;
import com.example.basic.global.common.response.model.SingleResponse;
import com.example.basic.global.common.response.service.ResponseService;
import com.example.basic.global.common.social.model.KakaoProfile;
import com.example.basic.global.security.JwtTokenProvider;
import com.example.basic.local.user.entity.User;
import com.example.basic.local.user.repository.UserJpaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

//@Tag(name = "sign", description = "the sign API")
//@RequiredArgsConstructor
//@RestController
//@RequestMapping(value = "/sign")
public class SocialSignController {

//    private final UserJpaRepository userJpaRepository;
//    private final JwtTokenProvider jwtTokenProvider;
//    private final ResponseService responseService;
//    private final PasswordEncoder passwordEncoder;
//
//    private final KakaoService kakaoService; //social login
//
//    @Operation(summary = "소셜 회원 로그인을 한다", tags = { "소셜 로그인" })
//    @PostMapping(value = "/signin/{provider}")
//    public SingleResponse<String> signinByProvider(
//            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Created user object", required = true)
//            @Parameter(name = "서비스 제공자 provider", description = "서비스 제공자 provider")
//            @PathVariable String provider,
//            @Parameter(name = "소셜 access_token", description = "소셜 access_token") @RequestParam String accessToken) {
//
//        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
//        User user = userJpaRepository.findByUidAndProvider(String.valueOf(profile.getId()), provider).orElseThrow(CustomUserNotFoundException::new);
//        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
//    }
//
//    @Operation(summary = "소셜 계정 회원가입을 한다", tags = { "소셜 계정 가입" })
//    @PostMapping(value = "/signup/{provider}")
//    public CommonResponse signupProvider(
//            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "서비스 제공자 provider", required = true)
//            @Parameter(name = "서비스 제공자 provider", description = "서비스 제공자 provider")
//            @PathVariable String provider,
//            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "소셜 access_token", required = true)
//            @Parameter(name = "소셜 access_token", description = "소셜 access_token")
//            @RequestParam String accessToken,
//            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "이름", required = true)
//            @Parameter(name = "이름", description = "이름")
//            @RequestParam String name) {
//
//        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
//        Optional<User> user = userJpaRepository.findByUidAndProvider(String.valueOf(profile.getId()), provider);
//        if (user.isPresent())
//            throw new CustomUserExistException();
//
//        User inUser = User.builder()
//                .uid(String.valueOf(profile.getId()))
//                .provider(provider)
//                .name(name)
//                .roles(Collections.singletonList("ROLE_USER"))
//                .build();
//
//        userJpaRepository.save(inUser);
//        return responseService.getSuccessResult();
//    }
}
