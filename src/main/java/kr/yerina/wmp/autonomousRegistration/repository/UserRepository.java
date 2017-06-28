package kr.yerina.wmp.autonomousRegistration.repository;

import kr.yerina.wmp.autonomousRegistration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByEmail(String email);
}
