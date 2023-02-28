package com.petdoctor.outer.api;

import com.petdoctor.outer.model.AppUser;
import com.petdoctor.outer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/petdoctor")
public class OuterController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
//    private final JwtEncoder jwtEncoder;
//    private final UserViewMapper userViewMapper;
//    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> tryGetAccess() {


        return null;
    }

    @PostMapping
    public ResponseEntity<?> tryPostAccess() {

        return null;
    }
}
