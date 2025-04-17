package kr.hhplus.be.server.interfaces.order;

import jakarta.validation.Valid;
import kr.hhplus.be.server.application.order.OrderFacade;
import kr.hhplus.be.server.application.order.OrderService;
import kr.hhplus.be.server.domain.order.Order;
import kr.hhplus.be.server.domain.order.OrderCommand;
import kr.hhplus.be.server.domain.order.OrderItemCommand;
import kr.hhplus.be.server.interfaces.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderFacade orderFacade;

    @PostMapping("/api/v1/users/{id}/order")
    public ApiResponse<OrderResponse.OrderV1> createOrder(@PathVariable("id") Long id, @Valid @RequestBody OrderRequest.CreateOrder request) {
        List<OrderItemCommand.AddOrderItem> addOrderItemList = request.getOrderItems().stream()
                .map(item -> OrderItemCommand.AddOrderItem.of(item.getProductId(), item.getQuantity()))
                .toList();

        OrderCommand.CreateOrder command = OrderCommand.CreateOrder.of(id, addOrderItemList);
        Order order = orderFacade.createOrder(command);

        return ApiResponse.success(OrderResponse.OrderV1.of(order));
    }
}
