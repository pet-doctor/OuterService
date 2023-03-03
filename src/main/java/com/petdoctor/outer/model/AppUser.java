package com.petdoctor.outer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class AppUser implements UserDetails {

    @JsonProperty("id")
    @ToString.Include
    @Indexed
    private String id;

    @JsonProperty("username")
    @NotNull
    @Size(min = 5, max = 20)
    @ToString.Include
    private String username;

    @JsonProperty("role")
    @ToString.Include
    private Role role = Role.USER;

    @JsonProperty("password")
    @NotNull
    @ToString.Include
    private String password;

    @JsonIgnore
    @ToString.Exclude
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
