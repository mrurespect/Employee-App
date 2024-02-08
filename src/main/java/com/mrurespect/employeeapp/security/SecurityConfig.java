package com.mrurespect.employeeapp.security;


import com.mrurespect.employeeapp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetails(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails mary =User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(susan,john,mary);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception {
        http.authorizeHttpRequests(configurer->
                    configurer
                            .requestMatchers(HttpMethod.GET,"/","/employees/list") .hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
                            .requestMatchers(HttpMethod.POST,"/employees/employees") .hasAnyRole("MANAGER","ADMIN")
                            .requestMatchers(HttpMethod.GET,"/employees/addEmployee") .hasAnyRole("MANAGER","ADMIN")
                            .requestMatchers(HttpMethod.GET,"/employees/enableChange/**") .hasAnyRole("MANAGER","ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/employees/employees") .hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE,"/employees/employees/**") .hasRole("ADMIN")
                            .requestMatchers("/register/**") .permitAll()


                            .anyRequest().authenticated()//any request to the app must be logged in
            ).formLogin(form->
                    form
                            .loginPage("/showMyLoginPage")
                            .loginProcessingUrl("/authenticateTheUser") //login form should post the data to this url for processing,no controller request needed for it
                            .successHandler(customAuthenticationSuccessHandler)
                            .permitAll() //allow any one to see the login page
            ).logout(logout->logout.permitAll()   //its neceesary for showing the logout message
            ).exceptionHandling(configurer->
                                        configurer.accessDeniedPage("/access-denied"));
        http.csrf(csrf->csrf.disable());


        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider provider(UserService userService){
        DaoAuthenticationProvider auth =new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth ;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}