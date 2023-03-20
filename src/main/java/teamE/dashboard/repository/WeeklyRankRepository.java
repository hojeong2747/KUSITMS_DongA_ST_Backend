package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teamE.dashboard.entity.WeeklyRank;

public interface WeeklyRankRepository extends JpaRepository<WeeklyRank, Long> {
//
//    // 1? prev = cur
//    @Query(nativeQuery = true, value =
//            "update weekly_rank w set w.prev_rank = w.cur_rank where w.year = 2023 and w.month = 3 and w.week = 2")
//    void updatePrev(); // 이 형태가 가능한지도 모르겠음
//
//    // 2? cur = 새 값
//    @Query(nativeQuery = true, value =
//            "UPDATE weekly_rank w\n" +
//                    "INNER JOIN (\n" +
//                    "  SELECT id, hit, \n" +
//                    "         (SELECT COUNT(*) + 1\n" +
//                    "          FROM weekly_rank wr\n" +
//                    "          WHERE wr.year = 2023 AND wr.month = 3 AND wr.week = 2 AND wr.hit > w.hit) AS new_rank\n" +
//                    "  FROM weekly_rank w\n" +
//                    "  WHERE w.year = 2023 AND w.month = 3 AND w.week = 2\n" +
//                    ") AS temp\n" +
//                    "ON w.id = temp.id\n" +
//                    "SET w.cur_rank = temp.new_rank")
//    void updateCur();
//
//    // 3? status 결정
//    @Query(nativeQuery = true, value =
//            "UPDATE weekly_rank\n" +
//                    "SET status = \n" +
//                    "  CASE\n" +
//                    "    WHEN prev_rank > cur_rank THEN 'UP'\n" +
//                    "    WHEN prev_rank < cur_rank THEN 'DOWN'\n" +
//                    "    ELSE 'KEEP'\n" +
//                    "  END")
//    void updateStatus();


}
