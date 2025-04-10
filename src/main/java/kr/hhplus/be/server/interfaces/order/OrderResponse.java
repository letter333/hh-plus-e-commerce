package kr.hhplus.be.server.interfaces.order;

import kr.hhplus.be.server.domain.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public class OrderResponse {
    @Getter
    @RequiredArgsConstructor
    public static class OrderV1 {
        private final Long id;

        public static OrderV1 of(Order order) {
            return new OrderV1(order.getOrderId());
        }
    }
}
