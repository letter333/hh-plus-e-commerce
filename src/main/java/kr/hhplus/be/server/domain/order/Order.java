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
        validation(totalPrice);
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public Order create(Long userId, Long totalPrice, List<OrderItem> orderItems) {
        return new Order(orderId, userId, totalPrice, orderItems);
    }

    public void calcTotalPrice() {
        this.totalPrice = orderItems.stream()
                .mapToLong(OrderItem::calcPrice)
                .sum();
    }

    private void validation(Long totalPrice) {
        if(totalPrice <= 0) {
            throw new IllegalArgumentException("잘못된 주문 금액입니다.");
        }
    }
}
