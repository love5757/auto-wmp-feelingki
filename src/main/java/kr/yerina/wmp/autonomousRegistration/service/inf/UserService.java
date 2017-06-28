package kr.yerina.wmp.autonomousRegistration.service.inf;

import kr.yerina.wmp.autonomousRegistration.entity.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
    List<User> finalAll();
    void updateUser(int userActive, String activeStatus);
}
