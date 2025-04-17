package kr.hhplus.be.server.domain.point;

import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository {
    Point findByUserId(long userId);

    Point save(Point point);
}
