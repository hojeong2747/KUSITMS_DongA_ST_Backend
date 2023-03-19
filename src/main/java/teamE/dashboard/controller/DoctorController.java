package teamE.dashboard.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import teamE.dashboard.service.DoctorService;

@Slf4j
@Api(tags = {"5.Doctor"})
@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;


}
