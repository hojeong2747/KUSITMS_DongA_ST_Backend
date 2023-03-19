package teamE.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamE.dashboard.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {


}
