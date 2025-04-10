package kr.hhplus.be.server.domain.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class OrderCommand {
    @Getter
    @NoArgsConstructor
    public static class CreateOrder {
        private Long userId;
        private List<OrderItemCommand.AddOrderItem> orderItems;

        private CreateOrder(Long userId, List<OrderItemCommand.AddOrderItem> orderItems) {
            this.userId = userId;
            this.orderItems = orderItems;
        }

        public static CreateOrder of(Long userId, List<OrderItemCommand.AddOrderItem> orderItems) {
            return new CreateOrder(userId, orderItems);
        }
    }
}
