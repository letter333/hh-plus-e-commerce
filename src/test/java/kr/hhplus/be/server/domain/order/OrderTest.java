package kr.hhplus.be.server.domain.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void 주문_생성_시_주문_품목의_수는_0보다_커야한다() {
        List<OrderItem> orderItems = new ArrayList<>();

        Assertions.assertThatThrownBy(
                () -> {
                    Order order = Order.builder()
                            .orderId(1L)
                            .orderItems(orderItems)
                            .build();
                }
        ).isInstanceOf(IllegalArgumentException.class);
    }
}