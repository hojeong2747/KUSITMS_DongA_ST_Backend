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
import teamE.dashboard.dto.part9.UploadDtoRes;
import teamE.dashboard.entity.Upload;
import teamE.dashboard.service.UploadService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = {"9.Upload"})
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @ApiOperation(value = "업로드 목록", notes = "업로드 목록")
    @GetMapping("/upload")
    public ResponseEntity<Result> getUploadList() {

        String date = uploadService.findToday();
        int totalCount = uploadService.findTotalCount(date);
        int undoneCount = uploadService.findUndoneCount(date);
        int doneCount = uploadService.findDoneCount(date);

        List<Upload> findUploadList = uploadService.findUploadList(date);

        List<UploadDtoRes> res = findUploadList.stream()
                .map(m -> new UploadDtoRes(m.getUploadStatus(), m.getTitle(), m.getLength(), m.getHospital(), m.getProfessor()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new Result(date, totalCount, undoneCount, doneCount, res), HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private String date;
        private int totalCount;
        private int undoneCount;
        private int doneCount;
        private T result;
    }
}
