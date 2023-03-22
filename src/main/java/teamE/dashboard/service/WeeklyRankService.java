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

    @Transactional
    public void updatePrevRank(int year, int month, int week) {
        weeklyRankRepository.updatePrev(year, month, week);
    }

    @Transactional
    public List<RowNum> getUpdateCurRank(int year, int month, int week) {
        List<Object[]> rns = weeklyRankRepository.updateCur(year, month, week);

        List<RowNum> list = new ArrayList<>();
        for (Object[] row : rns) {
            Number cur2_id = (Number) row[0];
            Number rn = (Number) row[1];
            list.add(new RowNum(cur2_id.longValue(), rn.intValue()));
        }

        List<WeeklyRank> rankList = new ArrayList<>();
        for (RowNum rn : list) {
            WeeklyRank wr = weeklyRankRepository.findById(rn.getCur2_id()).orElse(null);
            wr.setCurRank(rn.getRn());
            WeeklyRank saveRank = weeklyRankRepository.saveAndFlush(wr);
            rankList.add(wr);
        }

        return list;
    }

    @Transactional
    public void updateStatusValue () {
        weeklyRankRepository.updateStatus();
    }

    public List<WeeklyRank> findWeeklyRankTop6() {
        return weeklyRankRepository.findWeeklyRank();
    }
}
