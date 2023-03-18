package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.entity.BounceRate;
import teamE.dashboard.repository.BounceRateRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BounceRateService {

    private final BounceRateRepository bounceRateRepository;

    public List<BounceRate> findBounceRates(String date) {
        return bounceRateRepository.findBounceRates(date);
    }
}
