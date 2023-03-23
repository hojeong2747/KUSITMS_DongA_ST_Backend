package teamE.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.entity.Upload;
import teamE.dashboard.repository.UploadRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UploadService {

    private final UploadRepository uploadRepository;

    public String findToday() {
        return uploadRepository.getToday();
    }

    public int findTotalCount(String date) {
        return uploadRepository.getTotalCount(date);
    }

    public int findUndoneCount(String date) {
        return uploadRepository.getUndoneCount(date);
    }

    public int findDoneCount(String date) {
        return uploadRepository.getDoneCount(date);
    }

    public List<Upload> findUploadList(String date) {
        return uploadRepository.findUploadListByDate(date);
    }
}
