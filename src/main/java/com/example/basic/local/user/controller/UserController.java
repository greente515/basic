package com.example.basic.local.user.controller;

import com.example.basic.global.advice.exception.CustomUserNotFoundException;
import com.example.basic.global.common.response.model.CommonResponse;
import com.example.basic.global.common.response.model.ListResponse;
import com.example.basic.global.common.response.model.SingleResponse;
import com.example.basic.global.common.response.service.ResponseService;
import com.example.basic.local.user.entity.User;
import com.example.basic.local.user.repository.UserJpaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "user", description = "사용자 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class UserController {

    private final UserJpaRepository userRepository;

    private final ResponseService responseService; //공통 결과 서비스

    @Operation(summary = "회원 조회", tags = { "user" }, description = "회원 전체 조회")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
    @GetMapping("/users")
    public ListResponse<User> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userRepository.findAll());
    }
    @Operation(summary = "회원 단건  조회", tags = { "user" }, description = "userId로 회원을 조회한다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 조회 성공", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CustomUserNotFoundException.class))) })
    @GetMapping(value = "/user/{msrl}")
    public SingleResponse<User> findUserById(
            @PathVariable long msrl,
            @RequestParam String lang) {
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userRepository.findById(msrl).orElseThrow(CustomUserNotFoundException::new));
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PutMapping("/user")
    public SingleResponse<User> modify(
            @RequestParam long msrl,
            @RequestParam String uid,
            @RequestParam String name
    ) {
        User user = User.builder()
                .msrl(msrl)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userRepository.save(user));
    }

    @DeleteMapping("/user/{msrl}")
    public CommonResponse delete(
            @PathVariable long msrl
    ) {
        userRepository.deleteById(msrl);
        return responseService.getSuccessResult();
    }
}
