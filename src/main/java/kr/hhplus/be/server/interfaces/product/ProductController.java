package kr.hhplus.be.server.interfaces.product;

import kr.hhplus.be.server.application.product.ProductService;
import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.interfaces.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/api/v1/products")
    public ApiResponse<List<ProductResponse.ProductV1>> getProducts() {
        List<Product> products = productService.findAll();
        List<ProductResponse.ProductV1> productV1List = products.stream()
                .map(product -> ProductResponse.ProductV1.of(product.getProductId(), product.getName(), product.getPrice(), product.getStock()))
                .toList();

        return ApiResponse.success(productV1List);
    }

    @GetMapping("/api/v1/products/{id}")
    public ApiResponse<ProductResponse.ProductV1> getProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ApiResponse.success(ProductResponse.ProductV1.of(product.getProductId(), product.getName(), product.getPrice(), product.getStock()));

    }
}
