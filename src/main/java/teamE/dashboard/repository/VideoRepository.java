package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teamE.dashboard.entity.Video;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

//    @Query(value = "select v.id, v.hit, v.department,v.disease from Video v")
//    List<part2VideoDto> findVideosALl();

//    @Query(value = "select new teamE.dashboard.dto.part2.part2ByDepartmentRes(v.department,sum(v.hit)) from Video v group by v.department " +
//            "order by sum(v.hit) desc")
//    List<part2ByDepartmentRes> findHitsByDepartment();


    @Query(nativeQuery = true, value = "select title,length,hit,thumbnail) from Video v order by v.hit desc LIMIT 0,3")
    List<String> findVideoTop3();

    @Query(nativeQuery = true, value = "select department, sum(hit) / (select sum(hit) from video ) * 100 from video where video.status=0 group by video.department order by sum(hit) desc LIMIT 0,5 ")
    List<String> findHitsByDepartment();
    @Query(nativeQuery = true, value = "select sum(v.t)\n" +
            "from(\n" +
            "        SELECT department, SUM(hit) / (SELECT SUM(hit) FROM video WHERE status = 1) * 100 as t\n" +
            "        FROM (\n" +
            "                 SELECT * ,ROW_NUMBER() OVER (ORDER BY hit DESC) as rn\n" +
            "                 FROM video\n" +
            "                 WHERE status = 0\n" +
            "                 group by department\n" +
            "\n" +
            "             ) AS v\n" +
            "        WHERE rn > 5\n" +
            "        GROUP BY department\n" +
            "        ORDER BY SUM(hit) DESC\n" +
            "    ) as v ")
    String findHitsByDepartmentLast();

    @Query(nativeQuery = true, value = "select disease, sum(hit) / (select sum(hit) from video ) * 100 from video where video.status=0 group by video.disease order by sum(hit) desc LIMIT 0,5 ")
    List<String> findHitsByDisease();

    @Query(nativeQuery = true, value = "select sum(v.t)\n" +
            "from(\n" +
            "        SELECT disease, SUM(hit) / (SELECT SUM(hit) FROM video WHERE status = 1) * 100 as t\n" +
            "        FROM (\n" +
            "                 SELECT * ,ROW_NUMBER() OVER (ORDER BY hit DESC) as rn\n" +
            "                 FROM video\n" +
            "                 WHERE status = 0\n" +
            "                 group by disease\n" +
            "\n" +
            "             ) AS v\n" +
            "        WHERE rn > 5\n" +
            "        GROUP BY disease\n" +
            "        ORDER BY SUM(hit) DESC\n" +
            "    ) as v ")
    String findHitsByDiseaseLast();

    @Query(nativeQuery = true, value = "select non_medical, sum(hit) / (select sum(hit) from video ) * 100 from video where video.status=1 group by video.non_medical order by sum(hit) desc LIMIT 0,5 ")
    List<String> findHitsByNonMedical();

    @Query(nativeQuery = true, value = "select sum(v.t)\n" +
            "from(\n" +
            "        SELECT non_medical, SUM(hit) / (SELECT SUM(hit) FROM video WHERE status = 1) * 100 as t\n" +
            "        FROM (\n" +
            "                 SELECT * ,ROW_NUMBER() OVER (ORDER BY hit DESC) as rn\n" +
            "                 FROM video\n" +
            "                 WHERE status = 0\n" +
            "                 group by non_medical\n" +
            "\n" +
            "             ) AS v\n" +
            "        WHERE rn > 5\n" +
            "        GROUP BY non_medical\n" +
            "        ORDER BY SUM(hit) DESC\n" +
            "    ) as v ")
    String findHitsByNonMedicalLast();



}
