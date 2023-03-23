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
import teamE.dashboard.dto.part3.BounceRateDtoReq;
import teamE.dashboard.dto.part3.BounceRateDtoRes;
import teamE.dashboard.entity.BounceRate;
import teamE.dashboard.service.BounceRateService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"4.UV"})
@RestController
@RequiredArgsConstructor
public class BounceRateController {

    private final BounceRateService bounceRateService;

    // 이탈률 조회
    @ApiOperation(value = "UV, 재방문률, 신규방문률 조회", notes = "UV, 재방문률, 신규방문률 조회")
    @PostMapping("/uv")
    public ResponseEntity<Result> getBounceRate(@RequestBody BounceRateDtoReq req) {
        List<BounceRate> findBounceRates = bounceRateService.findBounceRates(req.getDate());

        List<BounceRateDtoRes> res = findBounceRates.stream()
                .map(m -> new BounceRateDtoRes(Integer.parseInt(m.getDate().substring(8))+"일", m.getRv(), m.getUv(), m.getUv() - m.getRv(), (int)(((double)m.getRv()/m.getUv()) * 100), (int)(((double)(m.getUv() - m.getRv())/m.getUv()) * 100)))
                .collect(Collectors.toList());
//        Collections.sort(res);


        return new ResponseEntity<>(new Result(res), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T result;
    }
}
