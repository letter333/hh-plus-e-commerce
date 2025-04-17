package kr.hhplus.be.server.domain.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponRepository {
    UserCoupon findByUserId(long userId);
    UserCoupon findByUserIdAndCouponId(long userId, long couponId);
    UserCoupon save(UserCoupon userCoupon);
}
