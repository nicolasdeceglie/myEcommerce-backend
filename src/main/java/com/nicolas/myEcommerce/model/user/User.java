package com.nicolas.myEcommerce.model.user;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uq_email", columnNames = "email"),
        @UniqueConstraint(name = "uq_phone_number", columnNames = "phone_number")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "phone_number")
    private Long phoneNumber;
    @Column(name = "create_date")
    private String createDate;
    @OneToMany(mappedBy="user",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Authority> authorities;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Address> addressList;
    @OneToMany(mappedBy = "user", fetch  = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Payment> payments;
}
