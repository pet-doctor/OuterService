package com.petdoctor.outer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Users")
public class AppUser implements Serializable {

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
}
