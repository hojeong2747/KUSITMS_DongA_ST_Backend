package teamE.dashboard.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamE.dashboard.dto.part7.MemoReq;
import teamE.dashboard.dto.part7.MemoRes;
import teamE.dashboard.entity.Memo;
import teamE.dashboard.repository.MemoRepository;
import teamE.dashboard.security.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    public List<MemoRes> getAllMemos() {
        return memoRepository.findAllMemo();
    }

    public MemoRes createMemos(MemoReq memoReq) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        String username = ((UserDetails) principal).getUsername();
        String profileImg;

        Optional<String> profileImgByUsername = userRepository.findProfileImgByUsername(username);
        if (profileImgByUsername.isPresent()) {
            profileImg = profileImgByUsername.get();
        }else{
            profileImg = "https://kusitsm27new.s3.us-east-2.amazonaws.com/default.png";
        }


        Memo newMemo = Memo.builder()

                .username(username)
                .status(0)
                .content(memoReq.getContent())
                .profileImg(profileImg)
                .content(memoReq.getContent())
                .date(memoReq.getDate())
                .build();

        memoRepository.save(newMemo);
        return new MemoRes(newMemo.getUsername(), newMemo.getProfileImg(), newMemo.getDate(), newMemo.getStatus(), newMemo.getContent());
    }
}
