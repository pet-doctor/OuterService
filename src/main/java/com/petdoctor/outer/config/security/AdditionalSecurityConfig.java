//package com.petdoctor.outer.config.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class AdditionalSecurityConfig {
//
//    private final UserDetailsService userDetailsService;
////
////    @Bean
////    public AuthenticationManager authenticationManager(
////            HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userService) throws Exception {
////        return http
////                .getSharedObject(AuthenticationManagerBuilder.class)
////                .userDetailsService(userService)
////                .passwordEncoder(passwordEncoder)
////                .and()
////                .build();
////    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return authenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public JwtEncoder jwtEncoder() {
////
////        var jwk = new RSAKey.Builder(this.rsaPublicKey).privateKey(this.rsaPrivateKey).build();
////        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
////        return new NimbusJwtEncoder(jwks);
////    }
////
////    @Bean
////    public JwtDecoder jwtDecoder() {
////        return NimbusJwtDecoder.withPublicKey(this.rsaPublicKey).build();
////    }
//}
