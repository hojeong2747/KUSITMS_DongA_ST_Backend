package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import teamE.dashboard.dto.part4.userInfoByAgeRes;
import teamE.dashboard.dto.part4.userInfoByDepartmentRes;
import teamE.dashboard.dto.part4.userInfoByFunnelsRes;
import teamE.dashboard.dto.part4.userInfoByRegionRes;
import teamE.dashboard.service.DoctorService;

import java.util.List;

@Slf4j
@Api(tags = {"5.Doctor"})
@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @ApiOperation(value = "유저 정보 유입 경로", notes = "유저 정보 유입 경로")
    @GetMapping("/userInfo/funnels")
    public ResponseEntity<List<userInfoByFunnelsRes>> getUserInfoByFunnelsTop5() {

        return new ResponseEntity<>(doctorService.getTop5ByFunnels(), HttpStatus.OK);
    }

    @ApiOperation(value = "유저 정보 진료과", notes = "유저 정보 진료과")
    @GetMapping("/userInfo/department")
    public ResponseEntity<List<userInfoByDepartmentRes>> getUserInfoByDepartmentTop5() {

        return new ResponseEntity<>(doctorService.getTop5ByDepartment(), HttpStatus.OK);
    }

    @ApiOperation(value = "유저 정보 연령대", notes = "유저 정보 연령대")
    @GetMapping("/userInfo/age")
    public ResponseEntity<List<userInfoByAgeRes>> getUserInfoByAgeTop5() {

        return new ResponseEntity<>(doctorService.getTop5ByAge(), HttpStatus.OK);
    }

    @ApiOperation(value = "유저 정보 지역", notes = "유저 정보 지역")
    @GetMapping("/userInfo/region")
    public ResponseEntity<List<userInfoByRegionRes>> getUserInfoByRegionTop5() {

        return new ResponseEntity<>(doctorService.getTop5ByRegion(), HttpStatus.OK);
    }


}
