package kr.hhplus.be.server.domain.point;

import kr.hhplus.be.server.domain.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void 충전_후_포인트는_최대_포인트를_넘을_수_없다() {
        User user = User.builder()
                .userId(1L)
                .name("오세룡")
                .build();

        Point point = Point.builder()
                .pointId(1L)
                .user(user)
                .point(10_000_000L)
                .build();

        Assertions.assertThatThrownBy(
                () -> point.charge(10_000L)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 사용_후_남는_포인트는_0보다_작을_수_없다() {
        User user = User.builder()
                .userId(1L)
                .name("오세룡")
                .build();

        Point point = Point.builder()
                .pointId(1L)
                .user(user)
                .point(1000L)
                .build();

        Assertions.assertThatThrownBy(
                () -> point.use(10000L)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}