package kr.hhplus.be.server.interfaces.point;

import kr.hhplus.be.server.domain.point.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public class PointResponse {
    @Getter
    @RequiredArgsConstructor
    public static class PointV1 {
        private final long point;

        public static PointV1 of(Point point) {
            return new PointV1(point.getPoint());
        }
    }
}
