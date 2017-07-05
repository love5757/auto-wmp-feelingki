package kr.yerina.wmp.autonomousRegistration.service.inf;

import kr.yerina.wmp.autonomousRegistration.entity.ErrorLog;
import kr.yerina.wmp.autonomousRegistration.repository.ErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ErrorLogService {
    @Autowired
    private ErrorLogRepository errorLogRepository;

    @Transactional
    public void save(ErrorLog errorLog) {
        errorLogRepository.save(errorLog);
    }
}
