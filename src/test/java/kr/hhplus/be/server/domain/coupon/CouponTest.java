package kr.hhplus.be.server.domain.coupon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {
    @Test
    void 쿠폰_적용전_금액은_0이하일_수_없다() {
        Coupon coupon = Coupon.builder()
                .couponId(1L)
                .discountValue(20)
                .type(Coupon.CouponType.RATIO)
                .isUsed(false)
                .build();

        Long amount = -1000L;

        Assertions.assertThatThrownBy(
                () -> coupon.use(amount)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 사용된_쿠폰은_사용할_수_없다() {
        Coupon coupon = Coupon.builder()
                .couponId(1L)
                .discountValue(20)
                .type(Coupon.CouponType.RATIO)
                .isUsed(true)
                .build();

        Assertions.assertThatThrownBy(
                () -> coupon.use(1000L)
        ).isInstanceOf(IllegalStateException.class);
    }
}