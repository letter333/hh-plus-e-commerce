package kr.hhplus.be.server.interfaces.point;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PointRequest {
    @Getter
    @NoArgsConstructor
    public static class Charge {
        @NotNull(message = "충전 금액은 필수입니다.")
        @Min(value = 1, message = "충전 금액은 최소 1포인트 이상이어야 합니다.")
        private long amount;
    }
}
