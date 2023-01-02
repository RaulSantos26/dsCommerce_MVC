package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String phone;

    @NonNull
    private LocalDateTime birthDate;

    @NonNull
    private String password;


    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();


}
