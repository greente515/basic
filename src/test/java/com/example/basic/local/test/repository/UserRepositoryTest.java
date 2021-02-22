package com.example.basic.local.test.repository;

import com.example.basic.local.user.entity.User;
import com.example.basic.local.user.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserJpaRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * JUnit 이용한 test
     * assert Method
     * assertNotNull(obj) - obj(객체)의 Null 여부 테스트
     * assertTrue(condition), assertFalse(condition) - condition(조건)의 참/거짓 테스트
     * assertEquals(obj1, obj2), assertNotEquals(obj1, obj2) - obj1 과 obj2 의 값이 같은지 테스트
     * assertSame(obj1, obj2) - obj1 과 obj2 의 객체가 같은지 테스트
     * assertArrayEquals(arr1, arr2) - 배열 arr1 과 arr2 가 같은지 테스트
     * assertThat(T actual, Matcher matcher) - 첫번째 인자에 비교대상, 두번째 로직에는 비교로직을 넣어 조건 테스트
     * ex) assertThat(a, is(100)) - a의 값이 100인가?
     *     assertThat(obj, is(nullValue())) - obj 가 null 인가?
     */
    @Test
    void whenFindByUid_thenReturnUser(){
        String uid = "test@test.com";
        String name = "test";
        //given
        userRepository.save(
                User.builder()
                .uid(uid)
                .name(name)
                .password("1234")
                .roles(Collections.singletonList("ROLE_USER"))
                .build()
        );
        //when
        Optional<User> user = userRepository.findByUid(uid);
        //then
        assertNotNull(user); //user 객체가 null 이 아닌지 체크
        assertTrue(user.isPresent()); //user 객체 존재여부 true/false 체크
        assertEquals(user.get().getName(), name); //user 객체의 name 과 name 변수 값이 같은지 체크
        assertThat(user.get().getName(), is(name)); //user 객체의 name 과 name 변수 값이 같은지 체크
    }

    @Test
    void 유저저장(){
       User user = User.builder()
                .uid("test@test.com")
                .name("test")
                .build();
        userRepository.save(user);
    }

    @Test
    void 유저리스트(){
        List<User> userList = userRepository.findAll();
        then(!userList.isEmpty());
    }

    @Test
    void insertNewUser() {
        //given
        userRepository.save(User.builder()
                .uid("happydaddy@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .name("happydaddy")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }


}