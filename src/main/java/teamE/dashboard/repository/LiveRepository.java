package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teamE.dashboard.dto.part8.LiveRes;
import teamE.dashboard.entity.Live;

import java.util.List;
import java.util.Optional;

public interface LiveRepository extends JpaRepository<Live, Long> {

//    @Query(value = "select v.id, v.hit, v.department,v.disease from Live v")
//    List<part2VideoDto> findVideosALl();

//    @Query(value = "select new teamE.dashboard.dto.part2.part2ByDepartmentRes(v.department,sum(v.hit)) from Video v group by v.department " +
//            "order by sum(v.hit) desc")
//    List<part2ByDepartmentRes> findHitsByDepartment();


    @Query(nativeQuery = true, value = "select department, sum(hit) / (select sum(hit) from live ) * 100 from live group by live.department order by sum(hit) desc LIMIT 0,4 ")
    List<String> findHitsByDepartment();

    @Query(nativeQuery = true, value = "select sum(v.t)\n" +
            "from(\n" +
            "        SELECT department, SUM(hit) / (SELECT SUM(hit) FROM live ) * 100 as t\n" +
            "        FROM (\n" +
            "                 SELECT * ,ROW_NUMBER() OVER (ORDER BY hit DESC) as rn\n" +
            "                 FROM live\n" +
            "                 WHERE status = 0\n" +
            "                 group by department\n" +
            "\n" +
            "             ) AS v\n" +
            "        WHERE rn > 4\n" +
            "        GROUP BY department\n" +
            "        ORDER BY SUM(hit) DESC\n" +
            "    ) as v ")
    String findHitsByDepartmentLast();

    @Query(nativeQuery = true, value = "select disease, sum(hit) / (select sum(hit) from live ) * 100 from live  group by live.disease order by sum(hit) desc LIMIT 0,4 ")
    List<String> findHitsByDisease();

    @Query(nativeQuery = true, value = "select sum(v.t)\n" +
            "from(\n" +
            "        SELECT disease, SUM(hit) / (SELECT SUM(hit) FROM live) * 100 as t\n" +
            "        FROM (\n" +
            "                 SELECT * ,ROW_NUMBER() OVER (ORDER BY hit DESC) as rn\n" +
            "                 FROM live\n" +
            "                 WHERE status = 0\n" +
            "                 group by disease\n" +
            "\n" +
            "             ) AS v\n" +
            "        WHERE rn > 4\n" +
            "        GROUP BY disease\n" +
            "        ORDER BY SUM(hit) DESC\n" +
            "    ) as v ")
    String findHitsByDiseaseLast();




    @Query(value = "select new teamE.dashboard.dto.part8.LiveRes(l.thumbnail,l.title,l.people,l.startTime,l.endTime) from Live l where l.status = 1")
    Optional<LiveRes> findRealtimeLive();





}
