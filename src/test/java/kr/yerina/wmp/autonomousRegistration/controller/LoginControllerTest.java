package kr.yerina.wmp.autonomousRegistration.controller;

import kr.yerina.wmp.autonomousRegistration.entity.Role;
import kr.yerina.wmp.autonomousRegistration.entity.User;
import kr.yerina.wmp.autonomousRegistration.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoginControllerTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void addRole() throws Exception {
        Role role = new Role();

        role.setId(1);
        role.setRole("USER");
        roleRepository.save(role);

        role.setId(2);
        role.setRole("ADMIN");
        roleRepository.save(role);
    }

    @Test
    public void registration() throws Exception {
    }

}