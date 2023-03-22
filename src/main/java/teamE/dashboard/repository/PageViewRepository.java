package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamE.dashboard.entity.PageView;

import java.util.List;

public interface PageViewRepository extends JpaRepository<PageView, Long> {

    @Query(value = "select * from page_view p " +
            "where p.category = :category " +
            "and p.date > date_sub(CONVERT(:date, DATE), interval 14 day)", nativeQuery = true)
    List<PageView> findPageViews(@Param("category") int category, @Param("date") String date);

    // 지금 데이터 다 안 넣어놔서 14일 전부터 ~ 파라미터로 들어오는 날까지 데이터 출력됨. 14로 바꾸면 2주!
}
