package com.example.basic.global.common.response.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends CommonResponse {

    private T data;
}
