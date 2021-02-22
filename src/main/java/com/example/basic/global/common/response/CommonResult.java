package com.example.basic.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CommonResult {

    SUCCESS(0, "성공"), FAIL(1, "실패");

    int code;
    String message;

}
