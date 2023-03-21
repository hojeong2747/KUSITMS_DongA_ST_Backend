package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamE.dashboard.entity.WeeklyRank;

import java.util.List;

public interface WeeklyRankRepository extends JpaRepository<WeeklyRank, Long> {

    // 1. prev = cur
    @Modifying(clearAutomatically = true)
    @Query("UPDATE WeeklyRank w SET w.prevRank = w.curRank WHERE w.year = :year AND w.month = :month AND w.week = :week")
    void updatePrev(@Param("year") int year, @Param("month") int month, @Param("week") int week);

    // 2. cur = 새 값
    @Query(value = "select id, ROW_NUMBER() OVER(order by hit DESC) as rn\n" +
            "    from weekly_rank where year = :year and month = :month and week = :week order by id ", nativeQuery = true)
    List<Object[]> updateCur(@Param("year") int year, @Param("month") int month, @Param("week") int week);


    // 3. status 결정
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE WeeklyRank w " +
            "SET w.status = CASE " +
            "  WHEN w.prevRank IS NOT NULL AND w.curRank IS NOT NULL AND w.prevRank > w.curRank THEN 'UP' " +
            "  WHEN w.prevRank IS NOT NULL AND w.curRank IS NOT NULL AND w.prevRank < w.curRank THEN 'DOWN' " +
            "  ELSE 'KEEP' " +
            "END " +
            "WHERE w.prevRank IS NOT NULL AND w.curRank IS NOT NULL")
    void updateStatus();

    // 4. 최종 curRank 정렬 후 상위 6개 보내기
    @Query(value = "select * from weekly_rank w order by w.cur_rank ASC LIMIT 0,6", nativeQuery = true)
    List<WeeklyRank> findWeeklyRank();

}
