package com.example.basic.global.common.social.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * kakao 유저 정보를 담을 객체
 */
@Getter @Setter
@ToString
public class KakaoProfile {

    private Long id;
    private Properties properties;

}
