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
import teamE.dashboard.dto.part2.part2ByLiveRes;
import teamE.dashboard.dto.part8.LiveRes;
import teamE.dashboard.entity.Live;
import teamE.dashboard.repository.LiveRepository;
import teamE.dashboard.service.LiveService;

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
    @ApiOperation(value = "[part3] 영상 top3 조회", notes = "[part3] 영상 top3 조회")
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

    @ApiOperation(value = "[part3] live 진료과목별 top5 조회", notes = "[part3] live 진료과목 별 top5 조회")
    @GetMapping("lives/topdepartment")
    public ResponseEntity<List<part2ByLiveRes>> getDepartmentTop5hits() {

        return new ResponseEntity<>(liveService.getHitsByDepartment(),HttpStatus.OK);
    }

    @ApiOperation(value = "[part3] live 질환별 top5 조회", notes = "[part3] llive 질환별 top5 조회")
    @GetMapping("lives/topdisease")
    public ResponseEntity<List<part2ByLiveRes>> getDiseaseTop5hits() {

        return new ResponseEntity<>(liveService.getHitsByDisease(),HttpStatus.OK);
    }


    @ApiOperation(value="[part8] 진행중인 라이브가 있다면 라이브 항목 return  \n 진행중인 라이브가 없을경우 각 항목 null 값 return ")
    @GetMapping("lives/realtime")
    public ResponseEntity<LiveRes> getRealtimeLive() {
        return new ResponseEntity<>(liveService.getRealtimeLive(),HttpStatus.OK);
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
