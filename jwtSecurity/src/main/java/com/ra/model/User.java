package com.ra.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_detail",
            joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "rid"))
    private Set<UserRole> roles;
}
