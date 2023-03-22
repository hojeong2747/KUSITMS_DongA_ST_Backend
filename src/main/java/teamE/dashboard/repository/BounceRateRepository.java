package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamE.dashboard.entity.BounceRate;

import java.util.List;

public interface BounceRateRepository extends JpaRepository<BounceRate, Long> {

    @Query(value = "select * from bounce_rate b " +
            "where b.date > date_sub(CONVERT(:date, DATE), interval 14 day) " +
            "and b.date <= CONVERT(:date, DATE) " +
            "order by date", nativeQuery = true)
    List<BounceRate> findBounceRates(@Param("date") String date);
}
