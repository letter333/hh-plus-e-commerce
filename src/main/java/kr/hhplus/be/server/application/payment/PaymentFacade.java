package kr.hhplus.be.server.application.payment;

import kr.hhplus.be.server.application.order.OrderService;
import kr.hhplus.be.server.application.point.PointService;
import kr.hhplus.be.server.application.product.ProductService;
import kr.hhplus.be.server.application.user.UserCouponService;
import kr.hhplus.be.server.application.user.UserService;
import kr.hhplus.be.server.domain.order.Order;
import kr.hhplus.be.server.domain.payment.Payment;
import kr.hhplus.be.server.domain.payment.PaymentCommand;
import kr.hhplus.be.server.domain.point.Point;
import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.user.UserCoupon;
import kr.hhplus.be.server.interfaces.payment.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFacade {
    private final PaymentService paymentService;
    private final OrderService orderService;
    private final UserCouponService userCouponService;
    private final UserService userService;
    private final PointService pointService;
    private final ProductService productService;

    public PaymentResponse.PaymentV1 processPayment(PaymentCommand.Process command) {
        User user = userService.findById(command.getUserId());
        Order order = orderService.findById(command.getOrderId());
        UserCoupon userCoupon = userCouponService.findByUserIdAndCouponId(command.getUserId(), command.getCouponId());

        Long totalPrice = order.getTotalPrice();

        if(userCoupon != null) {
            totalPrice = userCoupon.getCoupon().use(totalPrice);
        }

        Point point = pointService.getPoint(user.getUserId());

        Payment payment = Payment.builder()
                .method(command.getMethod())
                .totalPrice(totalPrice)
                .build();

        Payment savedPayment = paymentService.save(payment);

        // 결제 후 포인트 감소
        pointService.use(user.getUserId(), point.use(totalPrice));

        // 주문 후 재고 감소
        order.getOrderItems().forEach(item -> {
            Product product = productService.findById(item.getProductId());

            product.stockOut(item.getQuantity());

            productService.save(product);
        });


        return PaymentResponse.PaymentV1.of(savedPayment);
    }
}
