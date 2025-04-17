package kr.hhplus.be.server.domain.order;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderItemCommand {
    @Getter
    @NoArgsConstructor
    public static class AddOrderItem {
        private Long productId;
        private int quantity;

        private AddOrderItem(Long productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public static AddOrderItem of(Long productId, int quantity) {
            return new AddOrderItem(productId, quantity);
        }
    }
}
