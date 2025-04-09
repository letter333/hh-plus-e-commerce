package kr.hhplus.be.server.domain.product;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

class ProductTest {
    @Test
    void 상품_생성_시_이름은_필수() {
        String name = "";
        Long price = 10000L;

        Assertions.assertThatThrownBy(
                () -> Product.builder().name(name).price(price).build()
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상품_생성_시_가격은_0보다_작을_수_없다() {
        String name = "상품";
        Long price = -500L;

        Assertions.assertThatThrownBy(
                () -> Product.builder().name(name).price(price).build()
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 재고_차감_후_상품의_재고는_0보다_작을_수_없다() {
        String name = "상품";
        Long price = 10000L;
        int stock = 10;
        int amount = 20;

        Product product = Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();

        Assertions.assertThatThrownBy(
                () -> product.stockOut(amount)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}