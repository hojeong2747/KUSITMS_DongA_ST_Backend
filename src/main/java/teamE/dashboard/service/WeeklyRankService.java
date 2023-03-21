package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.dto.part6.RowNum;
import teamE.dashboard.entity.WeeklyRank;
import teamE.dashboard.repository.WeeklyRankRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeeklyRankService {

    private final WeeklyRankRepository weeklyRankRepository;

    // 주간 검색어 순위 전체 조회
    public List<WeeklyRank> findByCurRank() { return weeklyRankRepository.findAll(); }


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

//    @Transactional
//    public int updateNewRank(int year, int month, int week) {
//        return weeklyRankRepository.getNewRank(year, month, week);
//    }
//
//    @Transactional
//    public void updateCurRank(int year, int month, int week, int newRank) {
//        weeklyRankRepository.updateCur(year, month, week, newRank);
//    }

    // 다시
//    @Transactional
//    public List<Long> findUpdateId(int year, int month, int week) {
//        return weeklyRankRepository.findIdsToUpdate(year, month, week);
//    }
//    @Transactional
//    public void updateCurRank(List<Long> ids) {
//        weeklyRankRepository.updateCur(ids);
//    }

    @Transactional
    // 2 또 다시.
    public List<RowNum> getUpdateCur2(int year, int month, int week) {
        List<Object[]> rns = weeklyRankRepository.updateCur2(year, month, week);

        List<RowNum> list = new ArrayList<>();
        for (Object[] row : rns) {
            Number cur2_id = (Number) row[0];
            Number rn = (Number) row[1];
            list.add(new RowNum(cur2_id.longValue(), rn.intValue()));
        }
        System.out.println("list = " + list);

        List<WeeklyRank> rankList = new ArrayList<>();
        for (RowNum rn : list) {
            WeeklyRank wr = weeklyRankRepository.findById(rn.getCur2_id()).orElse(null);
            System.out.println("wr.getId() = " + wr.getId());
            System.out.println("wr.getCurRank() = " + wr.getCurRank());
            wr.setCurRank(rn.getRn());
            System.out.println("rn.getRn() = " + rn.getRn());
            WeeklyRank saveRank = weeklyRankRepository.saveAndFlush(wr);
            System.out.println("saveRank.getCurRank() = " + saveRank.getCurRank());
            rankList.add(wr);
        }
        weeklyRankRepository.saveAllAndFlush(rankList);
        System.out.println("rankList = " + rankList.toString());

        return list;
    }

    @Transactional
    public void updateStatusValue () {
        weeklyRankRepository.updateStatus();
    }

    @Transactional
    public void updatePrevRank(int year, int month, int week) {
        weeklyRankRepository.updatePrev(year, month, week);
    }

    public List<WeeklyRank> findWeeklyRanking() {
        return weeklyRankRepository.findWeeklyRank();
    }
}
