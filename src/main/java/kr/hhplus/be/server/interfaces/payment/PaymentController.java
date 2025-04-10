package kr.hhplus.be.server.interfaces.payment;

import jakarta.validation.Valid;
import kr.hhplus.be.server.application.payment.PaymentFacade;
import kr.hhplus.be.server.application.payment.PaymentService;
import kr.hhplus.be.server.domain.payment.PaymentCommand;
import kr.hhplus.be.server.interfaces.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentFacade paymentFacade;

    @PostMapping("/api/v1/payments")
    public ApiResponse<PaymentResponse.PaymentV1> processPayment(@Valid PaymentRequest.Process request) {
        PaymentCommand.Process command = PaymentCommand.Process.of(request.getUserId(), request.getOrderId(), request.getCouponId(), request.getMethod());

        return ApiResponse.success(paymentFacade.processPayment(command));
    }
}
