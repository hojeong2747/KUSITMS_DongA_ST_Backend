package teamE.dashboard.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //    User findByUsername(String username);
//    Optional<User> findByLoginId(String email);
    Optional<User> findByEmail(String email);

    @Query("select u.profileImg from User u where u.email=:email")
    Optional<String> findProfileImgByEmail(@Param(value="email") String email);


}
