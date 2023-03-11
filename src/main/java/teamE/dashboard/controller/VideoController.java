package teamE.dashboard.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import teamE.dashboard.entity.Video;
import teamE.dashboard.service.VideoService;

import javax.xml.transform.Result;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    // 영상 조회
    @GetMapping("videos")
    public ResponseEntity<Result> getVideos() {
        List<Video> findVideos = videoService.findVideos();

        List<VideoDto> collect = findVideos.stream()
                .map(m -> new VideoDto(m.getTitle(), m.getLength(), m.getHit(), m.getThumbnail()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Result(collect), HttpStatus.OK);

    }

    // top3 영상 조회
    @GetMapping("videos/top3")
    public ResponseEntity<Result> getTop3Videos() {
        List<Video> findVideos = videoService.findTop3Videos();

        List<VideoDto> collect = findVideos.stream()
                .map(m -> new VideoDto(m.getTitle(), m.getLength(), m.getHit(), m.getThumbnail()))
                .limit(3) // 3개만
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Result(collect), HttpStatus.OK);

    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T videoData;
    }

    @Data
    @AllArgsConstructor
    static class VideoDto {
        private String title;
        private String length;
        private int hit;
        private String thumbnail;
    }


}
