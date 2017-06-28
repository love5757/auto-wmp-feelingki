package kr.yerina.wmp.autonomousRegistration.service.impl;

import kr.yerina.wmp.autonomousRegistration.entity.Role;
import kr.yerina.wmp.autonomousRegistration.entity.User;
import kr.yerina.wmp.autonomousRegistration.repository.RoleRepository;
import kr.yerina.wmp.autonomousRegistration.repository.UserRepository;
import kr.yerina.wmp.autonomousRegistration.service.inf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(0);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public List<User> finalAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(int id, String activeStatus) {
        User user = userRepository.findOne(id);
        user.setActive(Integer.parseInt(activeStatus));
        userRepository.save(user);
    }
}
