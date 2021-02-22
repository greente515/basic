package com.example.basic.global.config;

import com.example.basic.global.oauth2.CustomAuthenticationProvider;
import com.example.basic.global.security.JwtAuthenticationFilter;
import com.example.basic.global.security.JwtTokenProvider;
import com.example.basic.global.security.exception.CustomAccessDeniedHandler;
import com.example.basic.global.security.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomAuthenticationProvider authenticationProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("pass")
//                .roles("USER");
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() //rest api 이므로 기본 설정 사용 안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 됨
                .csrf().disable() //rest api 이므로 csrf 보안 필요 없으므로 disable 처리
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt token 으로 인증할 것이므로 session 필요 없으므로 생성 안함
                .and()
                .authorizeRequests() //다음 리퀘스트에 대한 사용권한 체크
                .antMatchers("/*/signin", "/*/signin/**", "/*/signup", "/*/signup/**", "/social/**", "oauth2/callback", "/oauth/**", "/h2/console/*").permitAll() //가입 및 인증 주소는 누구나 접근 가능, social login 포함
                .antMatchers(HttpMethod.GET, "/exception/**", "/helloworld/**", "/hello/**", "/actuator/health", "/api/v1/**", "/test/board/**", "/favicon.ico").permitAll() //등록된 GET 리소스는 누구나 접근 가능, actuator health check
                .anyRequest().hasRole("USER") //그 외 나머지 요청은 모두 인증된 회원만 접근 가능
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); //jwt token 필터를 id/password 인증 필터 전에 넣기
    }

    /*
     * ignore swagger security config
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**");
    }

}
