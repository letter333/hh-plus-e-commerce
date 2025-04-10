package kr.hhplus.be.server.domain.coupon;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long couponId;
    private int discountValue;
    private CouponType type;
    private boolean isUsed;

    @Builder
    public Coupon(Long couponId, int discountValue, CouponType type, boolean isUsed) {
        this.couponId = couponId;
        this.discountValue = discountValue;
        this.type = type;
        this.isUsed = isUsed;
    }

    public enum CouponType {
        RATIO,
        AMOUNT
    }

    public Long use(Long price) {
        if(price <= 0) {
            throw new IllegalArgumentException("잘못된 결제금액 입니다.");
        }
        
        if(this.isUsed) {
            throw new IllegalStateException("이미 사용된 쿠폰입니다.");
        }

        Long resultPrice = 0L;

        if(this.type == CouponType.RATIO) {
            resultPrice = price - (price * this.discountValue / 100);
        } else if(this.type == CouponType.AMOUNT) {
            resultPrice = price - this.discountValue;
        }

        return resultPrice;
    }
}
