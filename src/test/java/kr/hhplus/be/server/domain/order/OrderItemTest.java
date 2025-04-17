package kr.hhplus.be.server.domain.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {
    @Test
    void 주문_품목의_개수는_0보다_커야한다() {
        int quantity = 0;

        Assertions.assertThatThrownBy(
                () -> {
                    OrderItem orderItem = OrderItem.builder()
                            .orderItemId(1L)
                            .productId(1L)
                            .quantity(quantity)
                            .price(1000L)
                            .build();
                }
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문_상품의_금액은_0_이상이어야_한다() {
        Long price = -1000L;

        Assertions.assertThatThrownBy(
                () -> {
                    OrderItem orderItem = OrderItem.builder()
                            .orderItemId(1L)
                            .productId(1L)
                            .quantity(1)
                            .price(price)
                            .build();
                }
        ).isInstanceOf(IllegalArgumentException.class);
    }
}