package kr.hhplus.be.server.interfaces.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public class ProductResponse {
    @Getter
    @RequiredArgsConstructor
    public static class ProductV1 {
        private final Long id;
        private final String name;
        private final Long price;
        private final int stock;

        public static ProductV1 of(Long id, String name, Long price,  int stock) {
            return new ProductV1(id, name, price,  stock);
        }
    }
}
