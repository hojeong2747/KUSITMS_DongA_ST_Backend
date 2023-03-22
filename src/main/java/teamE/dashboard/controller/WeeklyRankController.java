package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teamE.dashboard.dto.part6.RowNum;
import teamE.dashboard.dto.part6.WeeklyRankReq;
import teamE.dashboard.dto.part6.WeeklyRankRes;
import teamE.dashboard.entity.Video;
import teamE.dashboard.entity.WeeklyRank;
import teamE.dashboard.repository.WeeklyRankRepository;
import teamE.dashboard.service.WeeklyRankService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"6.WeeklyRank"})
@RestController
@RequiredArgsConstructor
public class WeeklyRankController {

    private final WeeklyRankService weeklyRankService;

//    @PostMapping("/weeklyRank/curRank")
    public ResponseEntity<List<RowNum>> getCurRank2(@RequestBody WeeklyRankReq req) {
        List<RowNum> rns = weeklyRankService.getUpdateCurRank(req.getYear(), req.getMonth(), req.getWeek());

        return new ResponseEntity<>(rns, HttpStatus.OK);
    }

//    @PostMapping("/weeklyRank/prevRank")
    public int updatePrevRanking(@RequestBody WeeklyRankReq req) {
        weeklyRankService.updatePrevRank(req.getYear(), req.getMonth(), req.getWeek());

        return 1;
    }

//    @GetMapping("/weeklyRank/status")
    public int updateWeeklyRankStatus() {
        weeklyRankService.updateStatusValue();

        return 1;
    }

//    @GetMapping("/weeklyRank/final")
    public ResponseEntity<List<WeeklyRankRes>> getWeeklyRanking() {
        List<WeeklyRank> findWeeklyRanking = weeklyRankService.findWeeklyRankTop6();

        List<WeeklyRankRes> res = findWeeklyRanking.stream()
                .map(m -> new WeeklyRankRes(m.getCurRank(), m.getKeyWord(), m.getStatus().toString()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // api 하나로 합치기
    @ApiOperation(value = "주간 검색어 순위", notes = "주간 검색어 순위")
    @PostMapping("/weeklyRank")
    public ResponseEntity<List<WeeklyRankRes>> getWeeklyRankTop6(@RequestBody WeeklyRankReq req) {
        // prev = cur
        weeklyRankService.updatePrevRank(req.getYear(), req.getMonth(), req.getWeek());

        // cur = 새 값
        weeklyRankService.getUpdateCurRank(req.getYear(), req.getMonth(), req.getWeek());

        // prev, cur 비교 -> status 결정
        weeklyRankService.updateStatusValue();

        // final curRank 로 정렬하고 6개 뽑아서 resDto 반환
        List<WeeklyRank> findWeeklyRanking = weeklyRankService.findWeeklyRankTop6();

        List<WeeklyRankRes> res = findWeeklyRanking.stream()
                .map(m -> new WeeklyRankRes(m.getCurRank(), m.getKeyWord(), m.getStatus().toString()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
