package kr.hhplus.be.server.application.payment;

import kr.hhplus.be.server.domain.order.Order;
import kr.hhplus.be.server.domain.payment.Payment;
import kr.hhplus.be.server.domain.payment.PaymentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    void 결제_저장_성공() {
        Order order = Order.builder()
                .orderId(1L)
                .userId(100L)
                .totalPrice(10000L)
                .build();

        Payment payment = Payment.builder()
                .paymentId(1L)
                .order(order)
                .totalPrice(10000L)
                .method(Payment.PayMethod.CARD)
                .build();

        Payment savedPayment = Payment.builder()
                .paymentId(1L)
                .order(order)
                .totalPrice(10000L)
                .method(Payment.PayMethod.CARD)
                .build();

        Mockito.when(paymentRepository.save(payment)).thenReturn(savedPayment);

        // when
        Payment result = paymentService.save(payment);

        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPaymentId()).isEqualTo(1L);
        Assertions.assertThat(result.getOrder()).isEqualTo(order);
        Assertions.assertThat(result.getTotalPrice()).isEqualTo(10000L);
        Assertions.assertThat(result.getMethod()).isEqualTo(Payment.PayMethod.CARD);

        Mockito.verify(paymentRepository).save(payment);
    }

}