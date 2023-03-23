package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamE.dashboard.dto.part3.PageViewDtoReq;
import teamE.dashboard.dto.part3.PageViewDtoRes;
import teamE.dashboard.entity.PageView;
import teamE.dashboard.service.PageViewService;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"3.PageView"})
@RestController
@RequiredArgsConstructor
public class PageViewController {

    private final PageViewService pageViewService;

    // 페이지뷰 카테고리별 조회
    @ApiOperation(value = "페이지뷰 카테고리별 조회", notes = "페이지뷰 카테고리별 조회")
    @PostMapping("/pageViews")
    public ResponseEntity<Result> getPageViews(@RequestBody PageViewDtoReq req) {
        List<PageView> findPageViews = pageViewService.findPageViews(req.getCategory(), req.getDate());

        List<PageViewDtoRes> res = findPageViews.stream()
                .map(m -> new PageViewDtoRes(Integer.parseInt(m.getDate().substring(8))+"일", m.getActiveUserCount()))
                .collect(Collectors.toList());
        Collections.sort(res);

        return new ResponseEntity<>(new Result(req.getCategory(), res), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int category;
        private T result;
    }
}
