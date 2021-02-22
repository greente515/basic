package com.example.basic.global.common.social.service;

import com.example.basic.global.common.social.model.KakaoProfile;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class KakaoServiceTest {

    @Autowired
    KakaoService kakaoService;

    @Test @Ignore
    void whenGetKakaoProfile_thenReturnProfile() {

        String accessToken = "xjsMzpQtIr4w13FIQvL3R7BW7X4yvm1KmzXCTwopyWAAAAFqMxEcwA";
        // given
        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        // then
        assertNotNull(profile);
        assertEquals(profile.getId(), Long.valueOf(1066788171));
    }
}