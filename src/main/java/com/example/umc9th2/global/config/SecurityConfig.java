package com.example.umc9th2.global.config;

import com.example.umc9th2.global.auth.entrypoint.AuthenticationEntryPointImpl;
import com.example.umc9th2.global.auth.jwt.JwtAuthFilter;
import com.example.umc9th2.global.auth.jwt.JwtUtil;
import com.example.umc9th2.global.auth.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    private final String[] allowUris = {
            "/login",
            "/sign-up",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // 폼로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // JwtAuthFilter를 UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint()))

        ;

        return http.build();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

}

//@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {//JWT 토큰 로직
//
//    private final JwtUtil jwtUtil;
//    private final CustomUserDetailsService customUserDetailsService;
//
//    private final String[] allowUris = {
//            "/login",
//            "/swagger-ui/**",
//            "/swagger-resources/**",
//            "/v3/api-docs/**"
//    };
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers(allowUris).permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                // 폼로그인 비활성화
//                .formLogin(AbstractHttpConfigurer::disable)
//                // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
//                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
//                .csrf(AbstractHttpConfigurer::disable)
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public JwtAuthFilter jwtAuthFilter() {
//        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//    //세션 관리자 로그인 로직
//    private final String[] allowUris = {
//            "/sign-up",
//            "/swagger-ui/**",
//            "/swagger-resources/**",
//            "/v3/api-docs/**",
//    };
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers(allowUris).permitAll()
//                        .requestMatchers("/swagger-ui/index.html").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .defaultSuccessUrl("/", true)
//                        .permitAll()
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

//@EnableWebSecurity          // Spring Security 활성화
//@Configuration
//public class SecurityConfig {

    // 인증 없이 접근 허용할 URI 목록
    //private final String[] allowUris = {
//            "/sign-up",             // 회원가입
//            "/swagger-ui/**",       // Swagger UI
//            "/swagger-resources/**",
//            "/v3/api-docs/**"
//    };

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                //인가(Authorization) 설정
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers(allowUris).permitAll() // 허용 URI
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자 전용
//                        .anyRequest().authenticated() // 나머지는 로그인 필요
//                )
//
//                // Session 기반 로그인 설정
//                .formLogin(form -> form
//                        // 로그인 성공 시 이동할 페이지
//                        .defaultSuccessUrl("/swagger-ui/index.html", true)
//                        .permitAll() // 로그인 페이지 접근 허용
//                )
//
//                //CSRF 비활성화
//                .csrf(AbstractHttpConfigurer::disable)
//
//                // 로그아웃 처리
//                .logout(logout -> logout
//                        .logoutUrl("/logout")             // 로그아웃 요청 URL
//                        .logoutSuccessUrl("/login?logout")// 로그아웃 후 이동
//                        .permitAll()
//                );
//
//        return http.build();
//    }

    //비밀번호 암호화 Bean (BCrypt)
//    @Bean
//    public PasswordEncoder passwordEncoder() {
 //       return new BCryptPasswordEncoder();
//    }
//}
