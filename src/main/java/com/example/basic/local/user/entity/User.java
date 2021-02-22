package com.example.basic.local.user.entity;

import com.example.basic.global.common.entity.DateEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "사용자")
@Builder //builder 를 사용할 수 있도록
@Entity //jpa entity 임
@Getter //getter 자동 생성
@Setter
@NoArgsConstructor //인자 없는 생성자 자동 생성
@AllArgsConstructor //인자 모두 갖춘 생성자 자동 생성
@Table(name = "user") //'user' 테이블과 매핑됨
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // PostEntity 에서 User 와의 관계를 Json 으로 변환시 오류 방지를 위한 코드
@Proxy(lazy = false) //user class 는 다른 class 에서 연관관계 매핑을 통해 lazy 로딩 되므로 캐싱시 문제가 발생하지 않도록 proxy false 설정을 함
public class User extends DateEntity implements UserDetails {


    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msrl;

    @Schema(description = "이메일", nullable = false, example = "test@test.com")
    @Column(nullable = false, unique = true, length = 30)
    private String uid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 100)
    private String password; //소셜로그인일 경우 패스워드가 없으므로 null 허용

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String provider; //회원의 서비스 제공자(카카오로그인, 구글로그인 사용에 의해 필요)

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.uid;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
