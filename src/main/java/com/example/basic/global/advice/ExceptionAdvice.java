package com.example.basic.global.advice;

import com.example.basic.global.advice.exception.*;
import com.example.basic.global.common.response.model.CommonResponse;
import com.example.basic.global.common.response.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    private final MessageSource messageSource;


//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected CommonResponse defaultException(HttpServletRequest request, Exception e){
//        return responseService.getFailResult();
//    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse defaultException(HttpServletRequest request, Exception e){
        //예외처리의 메세지를 messageSource에서 가져오도록 수정
        return responseService.getFailResult(Integer.valueOf(getMessage("unknown.code")), getMessage("unknown.msg"));
    }

    @ExceptionHandler(CustomUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse userNotFound(HttpServletRequest request, CustomUserNotFoundException e) {
        //예외처리의 메세지를 messageSource에서 가져오도록 수정
        return responseService.getFailResult(Integer.valueOf(getMessage("unknown.code")), getMessage("unknown.msg"));
    }

    @ExceptionHandler(CustomEmailSigninFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse emailSigninFailed(HttpServletRequest request, CustomEmailSigninFailedException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("emailSigninFailed.code")), getMessage("emailSigninFailed.msg"));
    }

    @ExceptionHandler(CustomAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponse authenticationEntryPointException(HttpServletRequest request, CustomAuthenticationEntryPointException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResponse accessDeniedException(HttpServletRequest request, AccessDeniedException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("accessDenied.code")), getMessage("accessDenied.msg"));
    }

    @ExceptionHandler(CustomCommunicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse communicationException(HttpServletRequest request, CustomCommunicationException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("communicationError.code")), getMessage("communicationError.msg"));
    }

    @ExceptionHandler(CustomUserExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse userExistException(HttpServletRequest request, CustomUserExistException e){
        return responseService.getFailResult(Integer.valueOf(getMessage("existingUser.code")), getMessage("existingUser.msg"));
    }

    @ExceptionHandler(CustomNotOwnerException.class)
    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    public CommonResponse notOwnerException(HttpServletRequest request, CustomNotOwnerException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("notOwner.code")), getMessage("notOwner.msg"));
    }

    @ExceptionHandler(CustomResourceNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse resourceNotExistException(HttpServletRequest request, CustomResourceNotExistException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("resourceNotExist.code")), getMessage("resourceNotExist.msg"));
    }

    @ExceptionHandler(CustomForbiddenWordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse forbiddenWordException(HttpServletRequest request, CustomForbiddenWordException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("forbiddenWord.code")), getMessage("forbiddenWord.msg", new Object[]{e.getMessage()}));
    }


    // code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }
    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
