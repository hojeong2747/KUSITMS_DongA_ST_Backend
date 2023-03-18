package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teamE.dashboard.dto.part2VideoDto;
import teamE.dashboard.entity.Live;
import teamE.dashboard.entity.Video;

import java.util.List;

public interface LiveRepository extends JpaRepository<Live, Long> {

//    @Query(value = "select v.id, v.hit, v.department,v.disease from Live v")
//    List<part2VideoDto> findVideosALl();

//    @Query(value = "select new teamE.dashboard.dto.part2ByDepartmentRes(v.department,sum(v.hit)) from Video v group by v.department " +
//            "order by sum(v.hit) desc")
//    List<part2ByDepartmentRes> findHitsByDepartment();


    @Query(nativeQuery = true, value = "select department, sum(hit) / (select sum(hit) from live ) * 100 from live group by live.department order by sum(hit) desc LIMIT 0,5 ")
    List<String> findHitsByDepartment();

    @Query(nativeQuery = true, value = "select disease, sum(hit) / (select sum(hit) from live ) * 100 from live group by live.disease order by sum(hit) desc LIMIT 0,5 ")
    List<String> findHitsByDisease();





}
