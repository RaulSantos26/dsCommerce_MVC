package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/*
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @ToString
    @Getter
    @Setter
    @RequiredArgsConstructor
    @NoArgsConstructor
*/


@Entity
@Table(name = "tb_category")
@Data
@NoArgsConstructor
public class Category {

    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "categories")
    @Setter(AccessLevel.NONE)
    private Set<Product> products = new HashSet<>();


}
