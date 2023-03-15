package teamE.dashboard.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //    User findByUsername(String username);
//    Optional<User> findByLoginId(String email);
    Optional<User> findByUsername(String username);

    @Query("select u.profileImg from User u where u.username=:username")
    Optional<String> findProfileImgByUsername(@Param(value="username") String username);


}
