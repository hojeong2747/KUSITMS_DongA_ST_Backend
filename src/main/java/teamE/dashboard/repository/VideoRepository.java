package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teamE.dashboard.entity.Video;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

//    @Query(value = "select v.id, v.hit, v.department,v.disease from Video v")
//    List<part2VideoDto> findVideosALl();

//    @Query(value = "select new teamE.dashboard.dto.part2ByDepartmentRes(v.department,sum(v.hit)) from Video v group by v.department " +
//            "order by sum(v.hit) desc")
//    List<part2ByDepartmentRes> findHitsByDepartment();


    @Query(nativeQuery = true, value = "select title,length,hit,thumbnail) from Video v order by v.hit desc LIMIT 0,3")
    List<String> findVideoTop3();

    @Query(nativeQuery = true, value = "select department, sum(hit) / (select sum(hit) from video ) * 100 from video where video.status=0 group by video.department order by sum(hit) desc LIMIT 0,7 ")
    List<String> findHitsByDepartment();

    @Query(nativeQuery = true, value = "select disease, sum(hit) / (select sum(hit) from video ) * 100 from video where video.status=0 group by video.disease order by sum(hit) desc LIMIT 0,7 ")
    List<String> findHitsByDisease();
    @Query(nativeQuery = true, value = "select non_medical, sum(hit) / (select sum(hit) from video ) * 100 from video where video.status=1 group by video.non_medical order by sum(hit) desc LIMIT 0,5 ")
    List<String> findHitsByNonMedical();




}
