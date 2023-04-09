package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamE.dashboard.entity.Upload;

import java.util.List;

public interface UploadRepository extends JpaRepository<Upload, Long> {

    @Query(value = "select MAX(u.date) from Upload u")
    String getToday();

    @Query(value = "select count(*) from Upload u where u.date = :date")
    int getTotalCount(@Param("date") String date);

    @Query(value = "select count(*) from Upload u where u.uploadStatus = '예정' and u.date = :date")
    int getUndoneCount(@Param("date") String date);


    @Query(value = "select count(*) from Upload u where u.uploadStatus = '완료' and u.date = :date")
    int getDoneCount(@Param("date") String date);

    @Query(value = "select * from upload where date = :date", nativeQuery = true)
    List<Upload> findUploadListByDate(@Param("date") String date);

}
