package kr.hhplus.be.server.domain.order;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    private Long price;

    @Builder
    public OrderItem(Long orderItemId, Order order, Long productId, int quantity, Long price) {
        validation(quantity, price);
        this.orderItemId = orderItemId;
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long calcPrice() {
        return this.price * this.quantity;
    }

    private void validation(int quantity, Long price) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("상품의 주문 수량을 확인해주세요.");
        }

        if(price == null || price < 0) {
            throw new IllegalArgumentException("상품의 금액을 확인해주세요.");
        }
    }
}
