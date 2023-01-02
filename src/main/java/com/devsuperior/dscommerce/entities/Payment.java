package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "tb_payment")
@Data
@NoArgsConstructor
public class Payment {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;


    @OneToOne
    @MapsId
    private  Order order;


}
