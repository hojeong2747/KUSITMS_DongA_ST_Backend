package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.dto.part4.userInfoByAgeRes;
import teamE.dashboard.dto.part4.userInfoByDepartmentRes;
import teamE.dashboard.dto.part4.userInfoByFunnelsRes;
import teamE.dashboard.dto.part4.userInfoByRegionRes;
import teamE.dashboard.entity.Age;
import teamE.dashboard.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;


    public List<userInfoByFunnelsRes> getTop5ByFunnels() {
        List<Object[]> userInfoByFunnels = doctorRepository.findUserInfoByFunnels();

        List<userInfoByFunnelsRes> list = new ArrayList<>();
        for (Object[] row : userInfoByFunnels) {
            String columnGroup = (String) row[0];
            Number percentage = (Number) row[1];
            list.add(new userInfoByFunnelsRes(columnGroup, percentage.intValue()));
        }
        return list;
    }

    public List<userInfoByDepartmentRes> getTop5ByDepartment() {
        List<Object[]> userInfoByDepartment = doctorRepository.findUserInfoByDepartment();

        List<userInfoByDepartmentRes> list = new ArrayList<>();
        for (Object[] row : userInfoByDepartment) {
            String columnGroup = (String) row[0];
            Number percentage = (Number) row[1];
            list.add(new userInfoByDepartmentRes(columnGroup, percentage.intValue()));
        }
        return list;
    }

    public List<userInfoByAgeRes> getTop5ByAge() {
        List<Object[]> userInfoByAge = doctorRepository.findUserInfoByAge();

        List<userInfoByAgeRes> list = new ArrayList<>();
        for (Object[] row : userInfoByAge) {
            String columnGroup = (String) row[0];
            Number percentage = (Number) row[1];
            System.out.println(columnGroup);
            if (Age.valueOf(columnGroup).getAgeNum() == 70) {
                list.add(new userInfoByAgeRes(Age.valueOf(columnGroup).getAgeNum() + "대 이상", percentage.intValue()));
            } else {
                list.add(new userInfoByAgeRes(Age.valueOf(columnGroup).getAgeNum() + "대", percentage.intValue()));
            }
        }
        return list;
    }

    public List<userInfoByRegionRes> getTop5ByRegion() {
        List<Object[]> userInfoByAge = doctorRepository.findUserInfoByRegion();

        List<userInfoByRegionRes> list = new ArrayList<>();
        for (Object[] row : userInfoByAge) {
            String columnGroup = (String) row[0];
            Number percentage = (Number) row[1];
            list.add(new userInfoByRegionRes(columnGroup, percentage.intValue()));
        }
        return list;
    }

}
