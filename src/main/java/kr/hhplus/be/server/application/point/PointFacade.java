package kr.hhplus.be.server.application.point;

import kr.hhplus.be.server.domain.point.PointCommand;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.application.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointFacade {
    private final PointService pointService;
    private final UserService userService;

    public void charge(PointCommand.Charge command) {
        User user = userService.findById(command.getUserId());
        pointService.charge(user.getUserId(), command.getAmount());
    }
}
