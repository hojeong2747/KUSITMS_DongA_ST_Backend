package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teamE.dashboard.entity.BounceRate;
import teamE.dashboard.service.BounceRateService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"4.BounceRate"})
@RestController
@RequiredArgsConstructor
public class BounceRateController {

    private final BounceRateService bounceRateService;

    // 이탈률 조회
    @ApiOperation(value = "이탈률 조회", notes = "이탈률 조회")
    @PostMapping("/bounceRates")
    public ResponseEntity<Result> getBounceRate(@RequestBody BounceRateDtoReq req) {
        List<BounceRate> findBounceRates = bounceRateService.findBounceRates(req.getDate());

        List<BounceRateDtoRes> res = findBounceRates.stream()
                .map(m -> new BounceRateDtoRes(m.getDate(), m.getExitUserCount(), m.getTotalUserCount(), ((double)m.getExitUserCount()/m.getTotalUserCount())*100))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Result(res), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T result;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class BounceRateDtoReq {
        private String date;

    }


    @Data
    @AllArgsConstructor
    static class BounceRateDtoRes {
        private String date;
        private Long exitUserCount;

        private Long totalUserCount;

        private double bounceRate; // 이탈률 퍼센트 -> 우선 double 형으로 계산
    }
}