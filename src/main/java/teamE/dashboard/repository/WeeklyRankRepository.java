package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamE.dashboard.entity.WeeklyRank;

public interface WeeklyRankRepository extends JpaRepository<WeeklyRank, Long> {
//
    // 1? prev = cur
    @Modifying(clearAutomatically = true)
    @Query(value =
            "update WeeklyRank w set w.prevRank = w.curRank where w.year = :year and w.month = :month and w.week = :week")
    void updatePrev(@Param("year") int year, @Param("month") int month, @Param("week") int week);

    // 2? cur = 새 값
//    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE WeeklyRank w\n" +
//            "SET w.curRank = (\n" +
//            "  SELECT COUNT(*) + 1\n" +
//            "  FROM WeeklyRank wr\n" +
//            "  WHERE wr.year = :year AND wr.month = :month AND wr.week = :week AND wr.hit > w.hit\n" +
//            ")\n" +
//            "WHERE w.year = :year AND w.month = :month AND w.week = :week\n")
//    void updateCur(@Param("year") int year, @Param("month") int month, @Param("week") int week);

    // 서버 실행되는데 api 연결 안 됨. update 안 됨.
//    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE WeeklyRank w\n" +
//            "SET w.curRank = ( SELECT COUNT(*) + 1 FROM WeeklyRank wr " +
//            "  WHERE wr.year = :year AND wr.month = :month AND wr.week = :week AND wr.hit > w.hit) " +
//            "  WHERE w.year = :year AND w.month = :month AND w.week = :week ")
//    void updateCur(@Param("year") int year, @Param("month") int month, @Param("week") int week);

    // 성공 함, 근데 hit..
//    @Query(value = "SELECT COUNT(*) + 1 FROM WeeklyRank wr WHERE wr.year = :year AND wr.month = :month AND wr.week = :week AND wr.hit > :hit")
//    int getNewRank(@Param("year") int year, @Param("month") int month, @Param("week") int week, @Param("hit") int hit);
//
//    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE WeeklyRank w SET w.curRank = :newRank WHERE w.year = :year AND w.month = :month AND w.week = :week AND w.hit = :hit")
//    void updateCur(@Param("year") int year, @Param("month") int month, @Param("week") int week, @Param("hit") int hit, @Param("newRank") int newRank);

    // 헐 성공..
    @Query(value = "SELECT COUNT(*) + 1 FROM WeeklyRank wr WHERE wr.year = :year AND wr.month = :month AND wr.week = :week")
    int getNewRank(@Param("year") int year, @Param("month") int month, @Param("week") int week);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE WeeklyRank w SET w.curRank = :newRank WHERE w.year = :year AND w.month = :month AND w.week = :week")
    void updateCur(@Param("year") int year, @Param("month") int month, @Param("week") int week, @Param("newRank") int newRank);


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



}
