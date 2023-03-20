package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teamE.dashboard.entity.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    @Query(nativeQuery = true, value =
            "WITH top_five AS (\n" +
                    "  SELECT funnels, COUNT(*) AS count\n" +
                    "  FROM doctor\n" +
                    "  GROUP BY funnels\n" +
                    "  ORDER BY count DESC\n" +
                    "  LIMIT 5\n" +
                    ")\n" +
                    "SELECT \n" +
                    "  CASE \n" +
                    "    WHEN funnels IN (SELECT funnels FROM top_five) THEN funnels \n" +
                    "    ELSE '기타' \n" +
                    "  END AS column_group,\n" +
                    "  COUNT(*) AS count,\n" +
                    "  COUNT(*) * 100.0 / (SELECT COUNT(*) FROM doctor) AS percentage\n" +
                    "FROM doctor\n" +
                    "GROUP BY column_group\n" +
                    "ORDER BY percentage DESC")
    List<String> findUserInfoByFunnels();


}
