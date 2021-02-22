package com.example.basic.global.common.response.service;

import com.example.basic.global.common.response.CommonResult;
import com.example.basic.global.common.response.model.CommonResponse;
import com.example.basic.global.common.response.model.ListResponse;
import com.example.basic.global.common.response.model.SingleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    //단일건
    public <T> SingleResponse<T> getSingleResult(T data){
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setData(data);
        setSuccessResult(singleResponse);
        return singleResponse;
    }

    //다중건
    public <T> ListResponse<T> getListResult(List<T> data){
        ListResponse<T> listResponse = new ListResponse<>();
        listResponse.setData(data);
        setSuccessResult(listResponse);
        return listResponse;
    }

    //성공 결과만
    public CommonResponse getSuccessResult(){
        CommonResponse commonResponse = new CommonResponse();
        setSuccessResult(commonResponse);
        return commonResponse;
    }

    //실패 결과만
//    public CommonResponse getFailResult(){
//        CommonResponse commonResponse = new CommonResponse();
//        commonResponse.setSuccess(false);
//        commonResponse.setCode(CommonResult.FAIL.getCode());
//        commonResponse.setMessage(CommonResult.FAIL.getMessage());
//        return commonResponse;
//    }

    public CommonResponse getFailResult(int code, String msg) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setSuccess(false);
        commonResponse.setCode(code);
        commonResponse.setMessage(msg);
        return commonResponse;
    }

    //결과에 api 요청 성공 세팅
    public void setSuccessResult(CommonResponse commonResponse){
        commonResponse.setSuccess(true);
        commonResponse.setCode(CommonResult.SUCCESS.getCode());
        commonResponse.setMessage(CommonResult.SUCCESS.getMessage());
    }
}
