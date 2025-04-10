package kr.hhplus.be.server.interfaces.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class OrderRequest {
    @Getter
    @NoArgsConstructor
    public static class CreateOrder {
        @NotNull(message = "주문 상품은 필수입니다.")
        @Size(min = 1, message = "주문 상품은 최소 1개 이상이어야 합니다.")
        private List<OrderItemRequest> orderItems;
    }

    @Getter
    @NoArgsConstructor
    public static class OrderItemRequest {
        @NotNull(message = "주문 상품은 필수입니다.")
        private Long productId;

        @NotNull(message = "주문 수량은 필수입니다.")
        @Min(value = 1, message = "주문 수량은 최소 1개 이상이어야 합니다.")
        private int quantity;
    }
}
