package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_product")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String description;

    @NonNull
    private Double price;

    @NonNull
    private String imgUrl;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();


    public List<Order> getOrders() {
        return items.stream().map(x -> x.getOrder()).toList();

    }
}
