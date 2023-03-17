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
import teamE.dashboard.entity.Live;
import teamE.dashboard.entity.Video;
import teamE.dashboard.repository.LiveRepository;
import teamE.dashboard.repository.VideoRepository;
import teamE.dashboard.service.LiveService;
import teamE.dashboard.service.VideoService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"2.Video"})
@RestController
@RequiredArgsConstructor
public class LiveController {
    private final LiveRepository liveRepository;

    private final LiveService liveService;

    // 영상 조회
    @ApiOperation(value = "영상 전체 조회", notes = "영상 전체 조회")
    @GetMapping("lives")
    public ResponseEntity<Result> getVideos() {
        List<Live> findVideos = liveService.findVideos();

        List<LiveDto> collect = findVideos.stream()
                .map(m -> new LiveDto(m.getTitle(), m.getLength(), m.getDate(), m.getHit(), m.getThumbnail()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Result(collect), HttpStatus.OK);

    }

    // top3 영상 조회
    @ApiOperation(value = "영상 top3 조회", notes = "영상 top3 조회")
    @GetMapping("lives/top3")
    public ResponseEntity<Result> getTop3Videos() {
        List<Live> findVideos = liveService.findTop3Videos();

        List<LiveDto> collect = findVideos.stream()
                .map(m -> new LiveDto(m.getTitle(), m.getLength(), m.getDate(), m.getHit(), m.getThumbnail()))
                .limit(3) // 3개만
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Result(collect), HttpStatus.OK);

    }

//    @GetMapping("videos/topdepartment")
//    public ResponseEntity<List<part2ByDepartmentRes>> getDepartmentTop7hits() {
//
//        return new ResponseEntity<>(videoRepository.findHitsByDepartment(),HttpStatus.OK);
//    }

    @ApiOperation(value = "live 진료과목별 top5 조회", notes = "live 진료과목 별 top5 조회")
    @GetMapping("lives/topdepartment")
    public ResponseEntity<List<String>> getDepartmentTop5hits() {

        return new ResponseEntity<>(liveRepository.findHitsByDepartment(),HttpStatus.OK);
    }

    @ApiOperation(value = "live 질환별 top5 조회", notes = "live 질환별 top5 조회")
    @GetMapping("lives/topdisease")
    public ResponseEntity<List<String>> getDiseaseTop5hits() {

        return new ResponseEntity<>(liveRepository.findHitsByDisease(),HttpStatus.OK);
    }



    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T liveData;
    }

    @Data
    @AllArgsConstructor
    static class LiveDto {
        private String title;
        private String length;
        private String date;
        private int hit;
        private String thumbnail;
    }


}
