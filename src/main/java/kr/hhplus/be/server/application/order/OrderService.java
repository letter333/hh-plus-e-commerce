package kr.hhplus.be.server.application.order;

import kr.hhplus.be.server.application.product.ProductService;
import kr.hhplus.be.server.domain.order.*;
import kr.hhplus.be.server.interfaces.order.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }
}
