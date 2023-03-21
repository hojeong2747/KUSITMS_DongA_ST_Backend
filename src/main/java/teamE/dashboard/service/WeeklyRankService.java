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


//    @Transactional
//    public void updateCurRank(int year, int month, int week) {
//        weeklyRankRepository.updateCur(year, month, week);
//    }

//    @Transactional
//    public int updateNewRank(int year, int month, int week, int hit) {
//        return weeklyRankRepository.getNewRank(year, month, week, hit);
//    }
//
//    @Transactional
//    public void updateCurRank(int year, int month, int week, int hit, int newRank) {
//        weeklyRankRepository.updateCur(year, month, week, hit, newRank);
//    }

    @Transactional
    public int updateNewRank(int year, int month, int week) {
        return weeklyRankRepository.getNewRank(year, month, week);
    }

    @Transactional
    public void updateCurRank(int year, int month, int week, int newRank) {
        weeklyRankRepository.updateCur(year, month, week, newRank);
    }

    @Transactional
    public void updateStatusValue () {
        weeklyRankRepository.updateStatus();
    }

    @Transactional
    public void updatePrevRank(int year, int month, int week) {
        weeklyRankRepository.updatePrev(year, month, week);
    }
}
