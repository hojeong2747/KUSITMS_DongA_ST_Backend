package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.dto.part2.part2ByLiveRes;
import teamE.dashboard.dto.part8.LiveRes;
import teamE.dashboard.entity.Live;
import teamE.dashboard.repository.LiveRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LiveService {

    private final LiveRepository liveRepository;

    // 영상 전체 조회
    public List<Live> findVideos() {
        return liveRepository.findAll();
    }

    // 영상 단건 조회
    public Live findOne(Long videoId) {
        return liveRepository.findById(videoId).get();
    }

    public List<Live> findTop3Videos() {
        return liveRepository.findAll(Sort.by(Sort.Direction.DESC, "hit"));
    }


    public List<part2ByLiveRes> getHitsByDisease() {
        List<String> hitsByDisease = liveRepository.findHitsByDisease();

        List<part2ByLiveRes> list = new ArrayList<>();
        for (String s : hitsByDisease) {
            String[] split = s.split(",");
            list.add(new part2ByLiveRes(split[0],split[1]));
        }
        list.add(new part2ByLiveRes("기타", liveRepository.findHitsByDiseaseLast()));
        return list;
    }
    public List<part2ByLiveRes> getHitsByDepartment() {
        List<String> hitsByDisease = liveRepository.findHitsByDepartment();

        List<part2ByLiveRes> list = new ArrayList<>();
        for (String s : hitsByDisease) {
            String[] split = s.split(",");
            list.add(new part2ByLiveRes(split[0],split[1]));
        }
        list.add(new part2ByLiveRes("기타", liveRepository.findHitsByDepartmentLast()));
        return list;
    }


    public LiveRes getRealtimeLive() {
        Optional<LiveRes> realtimeLive = liveRepository.findRealtimeLive();

        if (realtimeLive.isPresent()) {
            return realtimeLive.get();
        }else{
            return new LiveRes(null,null,0,null,null);
        }
    }
}
