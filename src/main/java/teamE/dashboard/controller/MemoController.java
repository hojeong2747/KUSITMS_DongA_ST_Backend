package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamE.dashboard.dto.part7.MemoReq;
import teamE.dashboard.dto.part7.MemoRes;
import teamE.dashboard.service.MemoService;

import java.util.List;

@Slf4j
@Api(tags = {"6.Memo"})
@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @ApiOperation(value = "메모 전체 조회", notes = "메모 전체 조회")
    @GetMapping("/memos")
    public ResponseEntity<List<MemoRes>> getMemos() {
        return new ResponseEntity<>(memoService.getAllMemos(), HttpStatus.OK);
    }

    @ResponseBody
    @ApiOperation(value="메모 생성",notes = "메모 생성. 로그인한 사용자로 이름 등록되니 로그인 후 create 부탁드립니다. ㅎㅎ")
    @PostMapping("/memos/add")
    public ResponseEntity<MemoRes> addMemo(@RequestBody MemoReq memoReq) {
        return new ResponseEntity<>(memoService.createMemos(memoReq), HttpStatus.OK);
    }
}
