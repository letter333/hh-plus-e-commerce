package kr.hhplus.be.server.domain.point;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class PointCommand {
    @Getter
    @NoArgsConstructor
    public static class Charge {
        private long userId;
        private long amount;

        private Charge(long userId, long amount) {
            this.userId = userId;
            this.amount = amount;
        }

        public static Charge of(long userId, long amount) {
            return new Charge(userId, amount);
        }
    }

}
