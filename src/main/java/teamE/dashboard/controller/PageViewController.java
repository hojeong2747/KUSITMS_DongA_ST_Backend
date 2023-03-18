package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamE.dashboard.entity.PageView;
import teamE.dashboard.service.PageViewService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"3.PageView"})
@RestController
@RequiredArgsConstructor
public class PageViewController {

    private final PageViewService pageViewService;

    // 페이지뷰 전체 조회
//    @GetMapping("pageViews")
//    public ResponseEntity<Result0> getPageViews() {
//        List<PageView> findPageViews = pageViewService.findPageViews();
//
//        List<PageViewDto> collect = findPageViews.stream()
//                .map(m -> new PageViewDto(m.getCategory(), m.getActiveUserCount(), m.getDate()))
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(new Result0(collect), HttpStatus.OK);
//    }


//    @Data
//    @AllArgsConstructor
//    static class Result0<T> {
//        private T result;
//    }

    //    @Data
//    @AllArgsConstructor
//    static class PageViewDto {
//        private int category;
//        private Long activeUserCount;
//        private String date;
//    }


    // 여기부터
    // 페이지뷰 카테고리별 조회
    @ApiOperation(value = "페이지뷰 카테고리별 조회", notes = "페이지뷰 카테고리별 조회")
    @PostMapping("/pageViews")
    public ResponseEntity<Result> getPageViewsByCategory(@RequestBody PageViewDtoReq req) {
        List<PageView> findPageViews = pageViewService.findPageViews(req.getCategory(), req.getDate());

        List<PageViewDtoRes> res = findPageViews.stream()
                .map(m -> new PageViewDtoRes(m.getActiveUserCount(), m.getDate()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Result(req.getCategory(), res), HttpStatus.OK);
    }
    // 우선은 req, res DTO 다 여기 작성함. 패키지 생성 후 추가하기.

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int category;
        private T result;
    }


    // 여기부터
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class PageViewDtoReq {
        private int category;
        private String date;

    }


    @Data
    @AllArgsConstructor
    static class PageViewDtoRes {
        //        private int category;
        private Long activeUserCount;
        private String date;
    }
}
