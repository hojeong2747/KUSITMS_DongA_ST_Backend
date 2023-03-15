package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamE.dashboard.entity.Video;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {


}
