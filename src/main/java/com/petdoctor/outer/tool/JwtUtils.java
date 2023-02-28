package com.petdoctor.outer.tool;

import com.petdoctor.outer.model.AppUser;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.time.Instant;

import static java.lang.String.format;

@UtilityClass
public class JwtUtils {

//    @Value("${jwt.expiry}")
    private Long expiry = 36000L;

    public JwtClaimsSet generateJwtClaimsSet(AppUser user, String scope) {

        Instant now = Instant.now();

        return JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(user.getUsername())
                .claim("roles", scope)
                .build();
    }


}
