package kr.hhplus.be.server.application.point;

import kr.hhplus.be.server.domain.point.Point;
import kr.hhplus.be.server.domain.point.PointRepository;
import kr.hhplus.be.server.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {
    @InjectMocks
    private PointService pointService;

    @Mock
    private PointRepository pointRepository;

    @Test
    void 포인트_조회_성공() {
        long userId = 1L;

        User user = User.builder()
                .userId(userId)
                .name("오세룡")
                .build();

        Point expectedPoint = Point.builder()
                .user(user)
                .point(5000L)
                .build();

        Mockito.when(pointRepository.findByUserId(userId)).thenReturn(expectedPoint);

        Point result = pointService.getPoint(userId);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPoint()).isEqualTo(5000L);

        Mockito.verify(pointRepository).findByUserId(userId);
    }

    @Test
    void 포인트_충전_성공() {
        long userId = 1L;
        long chargeAmount = 1000L;

        User user = User.builder()
                .userId(userId)
                .name("오세룡")
                .build();

        Point existingPoint = Point.builder()
                .pointId(1L)
                .user(user)
                .point(5000L)
                .build();

        Point updatedPoint = Point.builder()
                .pointId(1L)
                .user(user)
                .point(6000L)
                .build();

        Mockito.when(pointRepository.findByUserId(userId)).thenReturn(existingPoint);
        Mockito.when(pointRepository.save(Mockito.any(Point.class))).thenReturn(updatedPoint);

        Point result = pointService.charge(userId, chargeAmount);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPointId()).isEqualTo(1L);
        Assertions.assertThat(result.getUser().getUserId()).isEqualTo(userId);
        Assertions.assertThat(result.getPoint()).isEqualTo(6000L);

        Mockito.verify(pointRepository).save(Mockito.any(Point.class));
    }

    @Test
    void 최대_충전_금액_초과시_예외_발생() {
        long userId = 1L;
        long currentAmount = 1_000_000L;
        long chargeAmount = 100_000L;

        User user = User.builder()
                .userId(userId)
                .build();

        Point existingPoint = Point.builder()
                .pointId(1L)
                .user(user)
                .point(currentAmount)
                .build();

        Mockito.when(pointRepository.findByUserId(userId)).thenReturn(existingPoint);

        Assertions.assertThatThrownBy(() -> {
                    pointService.charge(userId, chargeAmount);
                }).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(pointRepository, Mockito.never()).save(Mockito.any(Point.class));
    }

    @Test
    void 포인트_사용_성공() {
        long userId = 1L;
        long useAmount = 3000L;

        User user = User.builder()
                .userId(userId)
                .name("오세룡")
                .build();

        Point existingPoint = Point.builder()
                .pointId(1L)
                .user(user)
                .point(5000L)
                .build();

        Point updatedPoint = Point.builder()
                .pointId(1L)
                .user(user)
                .point(2000L)
                .build();

        Mockito.when(pointRepository.findByUserId(userId)).thenReturn(existingPoint);
        Mockito.when(pointRepository.save(Mockito.any(Point.class))).thenReturn(updatedPoint);

        Point result = pointService.use(userId, useAmount);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPointId()).isEqualTo(1L);
        Assertions.assertThat(result.getUser().getUserId()).isEqualTo(userId);
        Assertions.assertThat(result.getPoint()).isEqualTo(2000L);

        Mockito.verify(pointRepository).save(Mockito.any(Point.class));
    }

    @Test
    void 잔액_부족시_예외_발생() {
        long userId = 1L;
        long useAmount = 6000L;

        User user = User.builder()
                .userId(userId)
                .build();

        Point existingPoint = Point.builder()
                .pointId(1L)
                .user(user)
                .point(5000L)
                .build();

        Mockito.when(pointRepository.findByUserId(userId)).thenReturn(existingPoint);

        Assertions.assertThatThrownBy(() -> {
                    pointService.use(userId, useAmount);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잔액이 부족합니다.");

        Mockito.verify(pointRepository, Mockito.never()).save(Mockito.any(Point.class));
    }
}