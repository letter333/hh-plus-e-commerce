package kr.hhplus.be.server.application.order;

import kr.hhplus.be.server.application.product.ProductService;
import kr.hhplus.be.server.domain.order.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderItemRepository orderItemRepository;

    public Order createOrder(OrderCommand.CreateOrder command) {
        Order order = new Order().create(command.getUserId(), null, null);

        List<OrderItem> orderItems = command.getOrderItems().stream()
                .map(itemCommand -> OrderItem.builder()
                        .order(order)
                        .productId(itemCommand.getProductId())
                        .quantity(itemCommand.getQuantity())
                        .price(productService.findById(itemCommand.getProductId()).getPrice())
                        .build())
                .toList();

        order.setOrderItems(orderItems);
        order.calcTotalPrice();

        return orderRepository.save(order);

    }
}
