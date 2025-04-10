package kr.hhplus.be.server.application.user;

import kr.hhplus.be.server.domain.user.UserCoupon;
import kr.hhplus.be.server.domain.user.UserCouponRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCouponService {
    private final UserCouponRepository userCouponRepository;

    public UserCoupon findByUserIdAndCouponId(Long userId, Long couponId) {
        return userCouponRepository.findByUserIdAndCouponId(userId, couponId);
    }
}
