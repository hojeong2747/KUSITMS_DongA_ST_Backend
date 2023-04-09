package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import teamE.dashboard.dto.part2.part2ByDepartmentRes;
import teamE.dashboard.dto.part2.part2ByDiseaseRes;
import teamE.dashboard.dto.part2.part2ByNonMedicalRes;
import teamE.dashboard.entity.Video;
import teamE.dashboard.repository.VideoRepository;
import teamE.dashboard.service.VideoService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"2.Video"})
@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoRepository videoRepository;

    private final VideoService videoService;

    // 영상 조회
    @ApiOperation(value = "영상 전체 조회", notes = "영상 전체 조회")
    @GetMapping("videos")
    public ResponseEntity<Result> getVideos() {
        List<Video> findVideos = videoService.findVideos();

        List<VideoDto> collect = findVideos.stream()
                .map(m -> new VideoDto(m.getTitle(), m.getLength(), m.getDate(), m.getHit(), m.getThumbnail(),m.getAge(),m.getLink()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Result(collect), HttpStatus.OK);

    }

    // top3 영상 조회
    @ApiOperation(value = "[part3] 영상 top3 조회", notes = "영상 top3 조회")
    @GetMapping("videos/top3")
    public ResponseEntity<Result> getTop3Videos() {
        List<Video> findVideos = videoService.findTop3Videos();

        List<VideoDto> collect = findVideos.stream()
                .map(m -> new VideoDto(m.getTitle(), m.getLength(), m.getDate(), m.getHit(), m.getThumbnail(),m.getAge(),m.getLink()))
                .limit(3) // 3개만
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Result(collect), HttpStatus.OK);

    }


//    @GetMapping("videos/topdepartment")
//    public ResponseEntity<List<part2ByDepartmentRes>> getDepartmentTop7hits() {
//
//        return new ResponseEntity<>(videoRepository.findHitsByDepartment(),HttpStatus.OK);
//    }
@ApiOperation(value = "[part3] video 진료과목별 top5 조회", notes = "[part3] live 진료과목 별 top5 조회")
@GetMapping("videos/topdepartment")
    public ResponseEntity<List<part2ByDepartmentRes>> getDepartmentTop5hits() {

        return new ResponseEntity<>(videoService.getHitsByDepartment(),HttpStatus.OK);
    }

    @ApiOperation(value = "[part3] video 질환별 top5 조회", notes = "[part3] live 진료과목 별 top5 조회")
    @GetMapping("videos/topdisease")
    public ResponseEntity<List<part2ByDiseaseRes>> getDiseaseTop5hits() {

        return new ResponseEntity<>(videoService.getHitsByDisease(),HttpStatus.OK);
    }
    @ApiOperation(value = "[part3] video 비진료 top5 조회", notes = "[part3] video 비진료 top5 조회")
    @GetMapping("videos/topnonmedical")
    public ResponseEntity<List<part2ByNonMedicalRes>> getNonMedicalTop5hits() {

        return new ResponseEntity<>(videoService.getHitsByNonMedical(),HttpStatus.OK);
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
        private String date;
        private int hit;
        private String thumbnail;
        private String age;
        private String link;
    }


}
