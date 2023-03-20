package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import teamE.dashboard.dto.part6.WeeklyRankRes;
import teamE.dashboard.entity.Video;
import teamE.dashboard.entity.WeeklyRank;
import teamE.dashboard.service.WeeklyRankService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"6.WeeklyRank"})
@RestController
@RequiredArgsConstructor
public class WeeklyRankController {

    private final WeeklyRankService weeklyRankService;

    // 주간 검색어 전체 조회
    @GetMapping("/weeklyRank")
    public ResponseEntity<List<WeeklyRankRes>> getWeeklyRank() {
        List<WeeklyRank> findWeeklyRanks = weeklyRankService.findWeeklyRank();

        List<WeeklyRankRes> res = findWeeklyRanks.stream()
                .map(m -> new WeeklyRankRes(m.getCurRank(), m.getKeyWord(), m.getStatus().toString()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
