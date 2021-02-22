package com.example.basic.global.common.response.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {

    @Schema(description = "응답의 성공 여부, true / false")
    private boolean success;

    @Schema(description = "응답 코드, >=0 ? 정상 : 비정상")
    private int code;

    @Schema(description = "응답 메세지")
    private String message;
}
