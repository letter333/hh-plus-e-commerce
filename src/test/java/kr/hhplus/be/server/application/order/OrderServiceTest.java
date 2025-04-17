package kr.hhplus.be.server.application.order;

import kr.hhplus.be.server.domain.order.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void 주문_조회_성공() {
        Long orderId = 1L;
        Long userId = 100L;

        OrderItem orderItem1 = OrderItem.builder()
                .orderItemId(1L)
                .productId(1001L)
                .quantity(2)
                .price(10000L)
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .orderItemId(2L)
                .productId(1002L)
                .quantity(1)
                .price(15000L)
                .build();

        List<OrderItem> orderItems = Arrays.asList(orderItem1, orderItem2);

        Order expectedOrder = Order.builder()
                .orderId(orderId)
                .userId(userId)
                .orderItems(orderItems)
                .totalPrice(35000L)
                .build();

        Mockito.when(orderRepository.findById(orderId)).thenReturn(expectedOrder);

        Order result = orderService.findById(orderId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getOrderId()).isEqualTo(orderId);
        Assertions.assertThat(result.getUserId()).isEqualTo(userId);
        Assertions.assertThat(result.getTotalPrice()).isEqualTo(35000L);
        Assertions.assertThat(result.getOrderItems()).hasSize(2);

        Mockito.verify(orderRepository).findById(orderId);
    }
}