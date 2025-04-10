package kr.hhplus.be.server.domain.point;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Point {
    private static final Long MAX_POINT = 1_000_000L;

    @Id
    @Column(name = "point_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Long point;

    @Builder
    public Point(Long pointId, User user, Long point) {
        this.pointId = pointId;
        this.user = user;
        this.point = point;
    }

    public Point(User user, Long point) {
        this.user = user;
        this.point = point;
    }

    public long charge(Long amount) {
        this.point += amount;

        if (this.point > MAX_POINT) {
            throw new IllegalArgumentException("최대 충전 금액을 초과했습니다.");
        }

        return this.point;
    }

    public long use(Long amount) {
        if (this.point < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        this.point -= amount;
        return this.point;
    }
}
