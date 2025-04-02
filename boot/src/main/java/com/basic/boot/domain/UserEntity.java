package com.basic.boot.domain;

import com.basic.boot.domain.request.UserRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id   // pk => not null, uniqe
    private String username;
    @Column(nullable=false)
    private String password;
    @Column(unique=true , nullable=false)
    private String email;

    public void update(UserRequest userRequest) {
        this.email = userRequest.getEmail();
    }
}
