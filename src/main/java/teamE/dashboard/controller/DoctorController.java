package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import teamE.dashboard.dto.part4.userInfoByFunnelsRes;
import teamE.dashboard.service.DoctorService;

import java.util.List;

@Slf4j
@Api(tags = {"5.Doctor"})
@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/userInfo/funnels")
    public ResponseEntity<List<userInfoByFunnelsRes>> getUserInfoByFunnelsTop5() {

        return new ResponseEntity<>(doctorService.getTop5ByFunnels(), HttpStatus.OK);
    }


}
