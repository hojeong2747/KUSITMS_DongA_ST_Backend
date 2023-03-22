package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.entity.PageView;
import teamE.dashboard.repository.PageViewRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PageViewService {

    private final PageViewRepository pageViewRepository;

    public List<PageView> findPageViews(int category, String date) {
        return pageViewRepository.findPageViews(category, date);
    }
}
