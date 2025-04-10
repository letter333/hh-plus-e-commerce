package kr.hhplus.be.server.domain.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void 주문_생성_시_주문_금액은_0보다_커야한다() {
        OrderItem orderItem1 = OrderItem.builder()
                .orderItemId(1L)
                .productId(1L)
                .quantity(2)
                .price(1000L)
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .orderItemId(2L)
                .productId(2L)
                .quantity(1)
                .price(2000L)
                .build();

        List<OrderItem> orderItems = List.of(orderItem1, orderItem2);

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = Order.builder()
                    .userId(1L)
                    .totalPrice(0L)
                    .orderItems(orderItems)
                    .build();
        });

        Order validOrder = Order.builder()
                .userId(1L)
                .totalPrice(4000L)
                .orderItems(orderItems)
                .build();

        validOrder.calcTotalPrice();
        assertEquals(4000L, validOrder.getTotalPrice());
    }

}