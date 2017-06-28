package kr.yerina.wmp.autonomousRegistration.controller;

import kr.yerina.wmp.autonomousRegistration.entity.Role;
import kr.yerina.wmp.autonomousRegistration.entity.User;
import kr.yerina.wmp.autonomousRegistration.repository.RoleRepository;
import kr.yerina.wmp.autonomousRegistration.repository.UserRepository;
import kr.yerina.wmp.autonomousRegistration.service.inf.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;



    //TODO 테스트 데이터
    @PostConstruct
    public void addRole(){
        Role role = new Role();

        role.setId(1);
        role.setRole("USER");
        roleRepository.save(role);

        role.setId(2);
        role.setRole("ADMIN");
        roleRepository.save(role);

    }

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "계정이 정상 등록 되었습니다. 계정 활성화 요청 후 로그인 하세요.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");

        modelAndView.addObject("userList",userService.finalAll());
        return modelAndView;
    }

    @RequestMapping(value="/userActive", method = RequestMethod.GET)
    public String home(@RequestParam(value = "id") int id, @RequestParam(value = "activeStatus") String activeStatus, HttpServletRequest request){
        userService.updateUser(id, activeStatus);
        return "redirect:/admin/home";
    }



}