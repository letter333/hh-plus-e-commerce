package kr.hhplus.be.server.domain.payment;

import kr.hhplus.be.server.domain.order.Order;
import kr.hhplus.be.server.domain.order.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void 결제_생성_시_총_금액은_0보다_커야한다() {
        Long totalPrice = 0L;
        OrderItem orderItem1 = OrderItem.builder()
                .orderItemId(1L)
                .productId(1L)
                .quantity(1)
                .price(1000L)
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .orderItemId(2L)
                .productId(2L)
                .quantity(3)
                .price(500L)
                .build();

        List<OrderItem> orderItems = List.of(orderItem1, orderItem2);

        Order order = Order.builder()
                .orderId(1L)
                .orderItems(orderItems)
                .build();

        Assertions.assertThatThrownBy(
                () -> {
                    Payment payment = Payment.builder()
                            .paymentId(1L)
                            .order(order)
                            .method(Payment.PayMethod.ACCOUNT)
                            .totalPrice(totalPrice)
                            .build();
                }
        ).isInstanceOf(IllegalArgumentException.class);
    }
}