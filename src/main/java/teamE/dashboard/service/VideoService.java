package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.dto.part2.part2ByDepartmentRes;
import teamE.dashboard.dto.part2.part2ByDiseaseRes;
import teamE.dashboard.dto.part2.part2ByNonMedicalRes;
import teamE.dashboard.entity.Video;
import teamE.dashboard.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

//@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    // 영상 전체 조회
    public List<Video> findVideos() {
        return videoRepository.findAll();
    }

    // 영상 단건 조회
    public Video findOne(Long videoId) {
        return videoRepository.findById(videoId).get();
    }

    public List<Video> findTop3Videos() {
        return videoRepository.findAll(Sort.by(Sort.Direction.DESC, "hit"));
    }

    public List<part2ByDepartmentRes> getHitsByDepartment() {
        List<String> hitsByDepartment = videoRepository.findHitsByDepartment();

        List<part2ByDepartmentRes> list = new ArrayList<>();
        for (String s : hitsByDepartment) {
            String[] split = s.split(",");
            list.add(new part2ByDepartmentRes(split[0],split[1]));
        }
        list.add(new part2ByDepartmentRes("기타",videoRepository.findHitsByDepartmentLast()));
//        log.info("기타"+videoRepository.findHitsByDepartmentLast());
        return list;
    }
    public List<part2ByDiseaseRes> getHitsByDisease() {
        List<String> hitsByDisease = videoRepository.findHitsByDisease();

        List<part2ByDiseaseRes> list = new ArrayList<>();
        for (String s : hitsByDisease) {
            String[] split = s.split(",");
            list.add(new part2ByDiseaseRes(split[0],split[1]));
        }
        list.add(new part2ByDiseaseRes("기타", videoRepository.findHitsByDiseaseLast()));
        return list;
    }

    public List<part2ByNonMedicalRes> getHitsByNonMedical() {
        List<String> hitsByDisease = videoRepository.findHitsByNonMedical();

        List<part2ByNonMedicalRes> list = new ArrayList<>();
        for (String s : hitsByDisease) {
            String[] split = s.split(",");
            list.add(new part2ByNonMedicalRes(split[0],split[1]));
        }
        list.add(new part2ByNonMedicalRes("기타", videoRepository.findHitsByNonMedicalLast()));
        return list;
    }


}
