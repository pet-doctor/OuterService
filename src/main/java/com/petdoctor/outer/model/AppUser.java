package com.petdoctor.outer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Users")
public class AppUser implements UserDetails {

    @JsonProperty("id")
    @Id
    @ToString.Include
    private String id;

    @JsonProperty("username")
    @NotNull
    @Size(min = 5, max = 20)
    @ToString.Include
    private String username;

    @JsonProperty("role")
    @ToString.Include
    private Role role = Role.USER;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @ToString.Include
    private String password;

    @JsonIgnore
    @ToString.Exclude
    private boolean enabled = true;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
