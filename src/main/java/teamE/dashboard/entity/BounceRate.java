package teamE.dashboard.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class BounceRate {
    // 이탈률 -> 재방문률, 신규방문률로 수정됨

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rv; // 재방문자수
    private Long uv; // 전체회원수
    private String date;
}
