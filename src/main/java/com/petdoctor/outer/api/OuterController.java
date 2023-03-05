package com.petdoctor.outer.api;

import com.petdoctor.outer.model.AppUser;
import com.petdoctor.outer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/petdoctor")
public class OuterController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> tryGetAccess() {


        return null;
    }

    @PostMapping
    public ResponseEntity<?> tryPostAccess() {

        return null;
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {

        try {

            return ResponseEntity.ok(
                    ((UserDetailsService)userService).loadUserByUsername(username));
        } catch(RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> getUser(@RequestBody AppUser user) {

        try {

            userService.registerUser(user);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
