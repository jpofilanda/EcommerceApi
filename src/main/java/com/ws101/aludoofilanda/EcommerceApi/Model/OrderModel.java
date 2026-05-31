package com.ws101.aludoofilanda.EcommerceApi.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a customer order in the e-commerce system.
 * One Order can have many OrderItems (One-to-Many relationship).
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString(exclude = "orderItems")
@EqualsAndHashCode(exclude = "orderItems")
@NoArgsConstructor
public class OrderModel {

    /**
     * Auto-generated primary key for the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the customer who placed the order.
     */
    private String customerName;

    /**
     * Date and time when the order was placed.
     */
    private LocalDateTime orderDate;

    /**
     * Current status of the order (e.g. PENDING, SHIPPED, DELIVERED).
     */
    private String status;

    /**
     * List of items in this order.
     * One Order has many OrderItems (One-to-Many relationship).
     * CascadeType.ALL means OrderItems are saved/deleted with the Order.
     * FetchType.LAZY means items are only loaded when accessed.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemModel> orderItems;
}