package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.entity.WeeklyRank;
import teamE.dashboard.repository.WeeklyRankRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeeklyRankService {

    private final WeeklyRankRepository weeklyRankRepository;

    // 주간 검색어 순위 전체 조회
    public List<WeeklyRank> findWeeklyRank() { return weeklyRankRepository.findAll(); }
}
