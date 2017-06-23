package kr.yerina.wmp.autonomousRegistration.service.inf;

import kr.yerina.wmp.autonomousRegistration.entity.User;

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}
