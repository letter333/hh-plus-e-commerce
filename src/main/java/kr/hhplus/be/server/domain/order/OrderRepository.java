package kr.hhplus.be.server.domain.order;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    Order findById(long id);
    Order save(Order order);
}
