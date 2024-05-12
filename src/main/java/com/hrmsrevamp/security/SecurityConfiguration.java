package com.hrmsrevamp.security;

import com.hrmsrevamp.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Security Configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public ApiAuthEntryPoint unauthorizedHandler() {
    return new ApiAuthEntryPoint();
  }

  @Bean
  public UserDetailsServiceImpl userDetailsService() {
    return new UserDetailsServiceImpl();
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
          throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request
                    .requestMatchers(HttpMethod.GET, "/ws-endpoint/**")
                    .permitAll()
                    /* use-auth APIs */
//                    .requestMatchers(HttpMethod.GET, "/api/auth/**").permitAll()
//
//                    .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
//
//                    .requestMatchers(HttpMethod.POST, "/api/auth/password")
//                    .hasAnyAuthority(RoleEnum.EMPLOYEE.name(), RoleEnum.MANAGER.name(),
//                            RoleEnum.MENTOR.name(), RoleEnum.HR.name(),
//                            RoleEnum.ADMIN.name())

                    /* Appraisal APIs */
                    .requestMatchers(HttpMethod.POST, "/api/**")
                    .permitAll()

                    .requestMatchers(HttpMethod.GET, "/api/**")
                    .permitAll()

//                    .requestMatchers(HttpMethod.GET, "/api/appraisal/**")
//                    .hasAnyAuthority(RoleEnum.EMPLOYEE.name(), RoleEnum.MANAGER.name(),
//                            RoleEnum.MENTOR.name(), RoleEnum.HR.name(),
//                            RoleEnum.ADMIN.name())
//
//                    .requestMatchers(HttpMethod.PUT, "/api/appraisal/**")
//                    .hasAnyAuthority(RoleEnum.EMPLOYEE.name(), RoleEnum.MANAGER.name(),
//                            RoleEnum.MENTOR.name(), RoleEnum.HR.name(),
//                            RoleEnum.ADMIN.name())

                    /* User APIs */
//                    .requestMatchers(HttpMethod.PUT, "/api/user/**")
//                    .hasAnyAuthority(RoleEnum.EMPLOYEE.name(), RoleEnum.MANAGER.name(),
//                            RoleEnum.MENTOR.name(), RoleEnum.HR.name(),
//                            RoleEnum.ADMIN.name())
                    .anyRequest().authenticated())
            .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
            .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler()))
            .authenticationProvider(daoAuthenticationProvider()).addFilterBefore(
                    jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
