package kr.hhplus.be.server.domain.payment;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Long totalPrice;
    private PayMethod method;

    public enum PayMethod {
        CARD, CASH, ACCOUNT
    }

    @Builder
    public Payment(Long paymentId, Order order, Long totalPrice, PayMethod method) {
        validation(totalPrice);
        this.paymentId = paymentId;
        this.order = order;
        this.totalPrice = totalPrice;
        this.method = method;
    }

    private void validation(Long totalPrice) {
        if(totalPrice <= 0) {
            throw new IllegalArgumentException("잘못된 결제금액입니다.");
        }
    }
}
