package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.entity.Video;
import teamE.dashboard.repository.VideoRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    // 영상 전체 조회
    public List<Video> findVideos() {
        return videoRepository.findAll();
    }

    // 영상 단건 조회
    public Video findOne(Long videoId) {
        return videoRepository.findById(videoId).get();
    }

    public List<Video> findTop3Videos() {
        return videoRepository.findAll(Sort.by(Sort.Direction.DESC, "hit"));
    }


}
