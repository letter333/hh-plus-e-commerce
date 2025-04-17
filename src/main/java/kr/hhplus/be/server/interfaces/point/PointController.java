package kr.hhplus.be.server.interfaces.point;

import jakarta.validation.Valid;
import kr.hhplus.be.server.domain.point.Point;
import kr.hhplus.be.server.domain.point.PointCommand;
import kr.hhplus.be.server.interfaces.common.ApiResponse;
import kr.hhplus.be.server.application.point.PointFacade;
import kr.hhplus.be.server.application.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;
    private final PointFacade pointFacade;

    @GetMapping("/api/v1/users/{id}/point")
    public ApiResponse<PointResponse.PointV1> getPoint(@PathVariable("id") long id) {
        Point point = pointService.getPoint(id);
        return ApiResponse.success(PointResponse.PointV1.of(point));
    }

    @PostMapping("/api/v1/users/{id}/point")
    public ApiResponse<Void> charge(@PathVariable("id") long id, @Valid @RequestBody PointRequest.Charge request) {
        PointCommand.Charge command = PointCommand.Charge.of(id, request.getAmount());

        pointFacade.charge(command);
        return ApiResponse.success();
    }
}
