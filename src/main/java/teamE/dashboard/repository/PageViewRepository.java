package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamE.dashboard.entity.PageView;

import java.util.List;

public interface PageViewRepository extends JpaRepository<PageView, Long> {

    @Query(value = "select * from page_view p " +
            "where p.category = :category " +
            "and p.date > date_sub(CONVERT(:date, DATE), interval 14 day) " +
            "and p.date <= CONVERT(:date, DATE) " +
            "order by date", nativeQuery = true)
    List<PageView> findPageViews(@Param("category") int category, @Param("date") String date);
}
