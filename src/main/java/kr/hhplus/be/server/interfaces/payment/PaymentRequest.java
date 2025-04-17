package kr.hhplus.be.server.interfaces.payment;

import jakarta.validation.constraints.NotNull;
import kr.hhplus.be.server.domain.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentRequest {
    @Getter
    @NoArgsConstructor
    public static class Process {
        @NotNull(message = "사용자 ID는 필수입니다.")
        private Long userId;
        @NotNull(message =  "주문 번호는 필수입니다.")
        private Long orderId;
        private Long couponId;
        @NotNull(message =  "결제 방법은 필수입니다.")
        private Payment.PayMethod method;
    }
}
