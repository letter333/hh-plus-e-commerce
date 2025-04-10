package kr.hhplus.be.server.domain.order;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    private Long userId;
    private Long totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order(Long orderId, Long userId, Long totalPrice, List<OrderItem> orderItems) {
        validation(orderItems, totalPrice);
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public void calcTotalPrice() {
        this.totalPrice = orderItems.stream()
                .mapToLong(OrderItem::calcPrice)
                .sum();
    }

    private void validation(List<OrderItem> orderItems, Long totalPrice) {
        if(orderItems.isEmpty()) {
            throw new IllegalArgumentException("주문 품목이 존재하지 않습니다.");
        }
    }
}
