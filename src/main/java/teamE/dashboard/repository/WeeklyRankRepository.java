package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamE.dashboard.entity.WeeklyRank;

import java.util.List;

public interface WeeklyRankRepository extends JpaRepository<WeeklyRank, Long> {

    // 1? prev = cur
    @Modifying(clearAutomatically = true)
    @Query(value =
            "update WeeklyRank w set w.prevRank = w.curRank where w.year = :year and w.month = :month and w.week = :week")
    void updatePrev(@Param("year") int year, @Param("month") int month, @Param("week") int week);

    // 2? cur = 새 값
    // 헐 성공..
//    @Query(value = "SELECT COUNT(*) + 1 FROM WeeklyRank wr WHERE wr.year = :year AND wr.month = :month AND wr.week = :week")
//    int getNewRank(@Param("year") int year, @Param("month") int month, @Param("week") int week);
//
//    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE WeeklyRank w SET w.curRank = :newRank WHERE w.year = :year AND w.month = :month AND w.week = :week")
//    void updateCur(@Param("year") int year, @Param("month") int month, @Param("week") int week, @Param("newRank") int newRank);

    // 성공 함, 근데 hit..
//    @Query(value = "SELECT COUNT(*) + 1 FROM WeeklyRank wr WHERE wr.year = :year AND wr.month = :month AND wr.week = :week AND wr.hit > :hit")
//    int getNewRank(@Param("year") int year, @Param("month") int month, @Param("week") int week, @Param("hit") int hit);
//
//    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE WeeklyRank w SET w.curRank = :newRank WHERE w.year = :year AND w.month = :month AND w.week = :week AND w.hit = :hit")
//    void updateCur(@Param("year") int year, @Param("month") int month, @Param("week") int week, @Param("hit") int hit, @Param("newRank") int newRank);

    // 다시 처음으로.. inner join , 1093..
//    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE WeeklyRank w\n" +
//            "SET w.curRank = ( SELECT COUNT(*) + 1 FROM WeeklyRank wr " +
//            "  WHERE wr.year = :year AND wr.month = :month AND wr.week = :week AND wr.hit > w.hit) " +
//            "  WHERE w.year = :year AND w.month = :month AND w.week = :week ")
//    void updateCur(@Param("year") int year, @Param("month") int month, @Param("week") int week);

    // 다시.
    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT w.id FROM WeeklyRank w " +
            "WHERE w.year = :year AND w.month = :month AND w.week = :week " +
            "AND w.hit > (SELECT MAX(wr.hit) FROM WeeklyRank wr " +
            "WHERE wr.year = :year AND wr.month = :month AND wr.week = :week)")
    List<Long> findIdsToUpdate(@Param("year") int year, @Param("month") int month, @Param("week") int week);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE WeeklyRank w " +
            "SET w.curRank = w.curRank + 1 " +
            "WHERE w.id IN (:ids)")
    void updateCur(@Param("ids") List<Long> ids);


    // 3? status 결정
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE WeeklyRank w " +
            "SET w.status = CASE " +
            "  WHEN w.prevRank IS NOT NULL AND w.curRank IS NOT NULL AND w.prevRank > w.curRank THEN 'UP' " +
            "  WHEN w.prevRank IS NOT NULL AND w.curRank IS NOT NULL AND w.prevRank < w.curRank THEN 'DOWN' " +
            "  ELSE 'KEEP' " +
            "END " +
            "WHERE w.prevRank IS NOT NULL AND w.curRank IS NOT NULL")
    void updateStatus();

    // 4. 최종 curRank 정렬해서 6개 보내기
    @Query(value = "select * from weekly_rank w order by w.cur_rank ASC LIMIT 0,6", nativeQuery = true)
    List<WeeklyRank> findWeeklyRank();


}
