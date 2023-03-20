package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.dto.part4.userInfoByFunnelsRes;
import teamE.dashboard.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;


    public List<userInfoByFunnelsRes> getTop5ByFunnels() {
        List<Object[]> userInfoByFunnels = doctorRepository.findCustomUserInfoByFunnels();

        List<userInfoByFunnelsRes> list = new ArrayList<>();
        for (Object[] row : userInfoByFunnels) {
            String columnGroup = (String) row[0];
            Number percentage = (Number) row[1];
            list.add(new userInfoByFunnelsRes(columnGroup, percentage.intValue()));
        }

        return list;
    }
}
