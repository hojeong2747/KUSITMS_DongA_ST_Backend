package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teamE.dashboard.dto.part7.MemoRes;
import teamE.dashboard.entity.Memo;

import java.util.List;


public interface MemoRepository extends JpaRepository<Memo, Long> {

    @Query(value = "select new teamE.dashboard.dto.part7.MemoRes(m.username,m.profileImg,m.date,m.status,m.content) from Memo m ")
    List<MemoRes> findAllMemo();

}
