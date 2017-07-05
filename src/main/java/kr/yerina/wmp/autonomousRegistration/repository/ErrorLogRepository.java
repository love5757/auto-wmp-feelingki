package kr.yerina.wmp.autonomousRegistration.repository;

import kr.yerina.wmp.autonomousRegistration.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}
