package com.ws101.aludoofilanda.EcommerceApi.Model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "order_items")
@Getter
@Setter
@ToString(exclude = {"order", "product"})
@EqualsAndHashCode(exclude = {"order", "product"})
@NoArgsConstructor
public class OrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double priceAtPurchase;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;
}