package kr.hhplus.be.server.application.product;

import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.domain.product.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void 전체_품목_조회_성공() {
        List<Product> expectedProducts = Arrays.asList(
                Product.builder()
                        .productId(1L)
                        .name("상품1")
                        .price(1000L)
                        .stock(10)
                        .build(),
                Product.builder()
                        .productId(2L)
                        .name("상품2")
                        .price(2000L)
                        .stock(20)
                        .build(),
                Product.builder()
                        .productId(3L)
                        .name("상품3")
                        .price(3000L)
                        .stock(30)
                        .build()
        );

        Mockito.when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> foundProducts = productService.findAll();

        Assertions.assertThat(foundProducts).isNotNull();
        Assertions.assertThat(foundProducts).hasSize(3);

        // 첫 번째 상품
        Assertions.assertThat(foundProducts.get(0).getProductId()).isEqualTo(1L);
        Assertions.assertThat(foundProducts.get(0).getName()).isEqualTo("상품1");
        Assertions.assertThat(foundProducts.get(0).getPrice()).isEqualTo(1000L);
        Assertions.assertThat(foundProducts.get(0).getStock()).isEqualTo(10);

        // 두 번째 상품
        Assertions.assertThat(foundProducts.get(1).getProductId()).isEqualTo(2L);
        Assertions.assertThat(foundProducts.get(1).getName()).isEqualTo("상품2");
        Assertions.assertThat(foundProducts.get(1).getPrice()).isEqualTo(2000L);
        Assertions.assertThat(foundProducts.get(1).getStock()).isEqualTo(20);

        // 세 번째 상품
        Assertions.assertThat(foundProducts.get(2).getProductId()).isEqualTo(3L);
        Assertions.assertThat(foundProducts.get(2).getName()).isEqualTo("상품3");
        Assertions.assertThat(foundProducts.get(2).getPrice()).isEqualTo(3000L);
        Assertions.assertThat(foundProducts.get(2).getStock()).isEqualTo(30);

        Mockito.verify(productRepository).findAll();
    }

    @Test
    void 품목_조회_성공() {
        Long productId = 1L;
        Product expectedProduct = Product.builder()
                .productId(productId)
                .name("상품")
                .price(1000L)
                .stock(1)
                .build();

        Mockito.when(productRepository.findById(1L)).thenReturn(expectedProduct);

        Product foundProduct = productService.findById(productId);

        Assertions.assertThat(foundProduct).isNotNull();
        Assertions.assertThat(foundProduct.getProductId()).isEqualTo(productId);
        Assertions.assertThat(foundProduct.getName()).isEqualTo("상품");
        Assertions.assertThat(foundProduct.getPrice()).isEqualTo(1000L);
        Assertions.assertThat(foundProduct.getStock()).isEqualTo(1);
    }

    @Test
    void 품목_저장_성공() {
        Product productToSave = Product.builder()
                .name("상품1")
                .price(2000L)
                .stock(5)
                .build();

        Product savedProduct = Product.builder()
                .productId(1L)
                .name("상품1")
                .price(2000L)
                .stock(5)
                .build();

        Mockito.when(productRepository.save(productToSave)).thenReturn(savedProduct);

        Product result = productService.save(productToSave);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getProductId()).isEqualTo(1L);
        Assertions.assertThat(result.getName()).isEqualTo("상품1");
        Assertions.assertThat(result.getPrice()).isEqualTo(2000L);
        Assertions.assertThat(result.getStock()).isEqualTo(5);

        Mockito.verify(productRepository).save(productToSave);
    }

}