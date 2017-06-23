package kr.yerina.wmp.autonomousRegistration.repository;

import kr.yerina.wmp.autonomousRegistration.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
