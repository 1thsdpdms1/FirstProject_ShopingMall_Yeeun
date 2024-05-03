package org.spring.e1i4TeamProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigClass {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Post (웹페이지 공격 차단)
        http.csrf().disable(); // Post -> 웹 보안 설정

        // 1. 웹페이지 설정 (Client 요청 URL 처리, 권한)
        http.authorizeHttpRequests()
            .antMatchers("/", "/member/memberJoin","/member/memberList", "/member/memberLogin", "/member/").permitAll()
            .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
            .antMatchers("/member/logout","/board/write").authenticated()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/board/inquiry", "/board/answer").permitAll()

            .antMatchers( "/member/shop/**").hasAnyRole("ADMIN", "MANAGER")
            .antMatchers("/member/detail/**").hasAnyRole("ADMIN", "MANAGER", "MEMBER");

        // 2. 로그인
        http.formLogin()
            // 사용자가 직접 설정한 로그인 페이지
            .loginPage("/member/memberLogin")
            // *** username : 실제로는 userEmail
            .usernameParameter("userEmail")
            // *** password : 실제로는 userPw
            .passwordParameter("userPw")

            // 로그인 폼 post <form th:action="@{/member/memberLogin}" method="post">
            .loginProcessingUrl("/member/memberLogin")
            // 로그인 성공시
//            .defaultSuccessUrl("/")
//            .successForwardUrl("/") // post방식 컨트롤러에 만들어야 한다.
            .successHandler(customAuthenticationSuccessHandler())
            // 로그인 실패시
//            .failureForwardUrl("/member/fail")
            .failureHandler(authenticationFailureHandler())
            .and()
            .oauth2Login()
            .loginPage("/member/memberLogin")
            .userInfoEndpoint() // google, naver, kakao
            .userService(myOAuth2UserService());

        // 3. 로그아웃
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
            .logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> myOAuth2UserService() {
        return new MyDefaultOAuth2UserService();
    }

    // 시큐리티 로그인 성공시
    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
    }

    // 시큐리티 로그인 실패시
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }
}
