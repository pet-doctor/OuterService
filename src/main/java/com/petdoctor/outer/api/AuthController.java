package com.petdoctor.outer.api;

import com.petdoctor.outer.model.AppUser;
import com.petdoctor.outer.service.UserService;
import com.petdoctor.outer.tool.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.joining;

@RestController
@RequestMapping("/petdoctor/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@EnableWebSecurity
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AppUser> login(@RequestBody AppUser user) {
        try {
            var authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            var scope =
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(joining(" "));

            var claims = JwtUtils.generateJwtClaimsSet(user, scope);

            var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(user);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {

        return ResponseEntity.ok(userService.registerUser(user));
    }
}
