package com.example.basic.global.security.service;

import com.example.basic.global.advice.exception.CustomUserNotFoundException;
import com.example.basic.global.common.CacheKey;
import com.example.basic.local.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Cacheable(value = CacheKey.USER, key = "#userPk", unless = "#result == null")
    //value 에는 저장시 키값, key 는 키 생성시 추가로 덧붙일 파라미터 정보 선언 ex)user::1004 와 같은 형태로 생성
    //unless 메서드 결과가 null 이 아닌 경우에만 캐싱하도록 하는 옵션
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        return userJpaRepository.findById(Long.valueOf(userPk)).orElseThrow(CustomUserNotFoundException::new);
    }
}
