package com.example.basic.global.common.response.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponse<T> extends CommonResponse {

    private List<T> data;
}
