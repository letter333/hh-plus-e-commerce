package kr.hhplus.be.server.domain.coupon;

import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository {
    void save(Coupon coupon);
    Coupon findById(long id);
}
