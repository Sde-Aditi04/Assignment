package com.example.Authorization.Config;


import com.example.Authorization.security.JwtAuthenticationEntryPoint;
import com.example.Authorization.security.JwtAuthenticationFilter;
import com.example.Authorization.security.JwtUtil;
import com.example.Authorization.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtil, userDetailsService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(auth.userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .dispatcherTypeMatchers(HttpMethod.valueOf("/api/public/**")).permitAll()
//                .dispatcherTypeMatchers(HttpMethod.valueOf("/api/private/**")).authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests(requests -> requests
                    .requestMatchers("/api/public/**").permitAll()
                    .requestMatchers("/api/private/**").hasAuthority("ROLE_USER")
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .permitAll()
            )
            .logout(logout -> logout
                    .permitAll());

    return http.build();
}

}
