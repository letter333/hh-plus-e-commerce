package kr.hhplus.be.server.domain.product;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> findAll();
    Product findById(long id);
    Product save(Product product);
}
