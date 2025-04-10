package kr.hhplus.be.server.interfaces.payment;

import kr.hhplus.be.server.domain.payment.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public class PaymentResponse {
    @Getter
    @RequiredArgsConstructor
    public static class PaymentV1 {
        private final Long paymentId;

        public static PaymentV1 of(Payment payment) {
            return new PaymentV1(payment.getPaymentId());
        }
    }
}
