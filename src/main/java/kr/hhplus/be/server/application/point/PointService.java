package kr.hhplus.be.server.application.point;

import kr.hhplus.be.server.domain.point.Point;
import kr.hhplus.be.server.domain.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public Point getPoint(Long userId) {
        return pointRepository.findByUserId(userId);
    }

    public Point charge(Long userId, Long amount) {
        Point point = pointRepository.findByUserId(userId);
        point.charge(amount);

        return pointRepository.save(point);
    }

    public Point use(Long userId, Long amount) {
        Point point = pointRepository.findByUserId(userId);
        point.use(amount);

        return pointRepository.save(point);
    }
}
