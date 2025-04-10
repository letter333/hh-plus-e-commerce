package kr.hhplus.be.server.domain.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentCommand {
    @Getter
    @NoArgsConstructor
    public static class Process {
        private Long userId;
        private Long orderId;
        private Long couponId;
        private Payment.PayMethod method;

        private Process(Long userId, Long orderId, Long couponId, Payment.PayMethod method) {
            this.userId = userId;
            this.orderId = orderId;
            this.couponId = couponId;
            this.method = method;
        }

        public static Process of(Long userId, Long orderId, Long couponId, Payment.PayMethod method) {
            return new Process(userId, orderId, couponId, method);
        }
    }

}
