package kr.hhplus.be.server.domain.product;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long price;
    private int stock;

    @Builder
    public Product(Long productId, String name, Long price, int stock) {
        validation(name, price);
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    private void validation(String name, Long price) {
        if(name == null || name.isBlank() == true) {
            throw new IllegalArgumentException("상품 이름은 필수입니다.");
        }

        if(price == null || price <= 0) {
            throw new IllegalArgumentException("가격은 0보다 작을 수 없습니다.");
        }
    }

    public void stockOut(int quantity) {
        if(this.stock < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stock -= quantity;
    }
}
