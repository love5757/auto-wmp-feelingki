package kr.yerina.wmp.autonomousRegistration.controller;


import kr.yerina.wmp.autonomousRegistration.entity.User;
import kr.yerina.wmp.autonomousRegistration.properties.WmpServiceProperties;
import kr.yerina.wmp.autonomousRegistration.entity.Work;
import kr.yerina.wmp.autonomousRegistration.repository.WorksRepository;
import kr.yerina.wmp.autonomousRegistration.slack.SlackNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.Segment;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by philip on 2017-05-31.
 */
@Slf4j
@Controller
@RefreshScope
public class WmpHomeController {

    @Autowired
    private WorksRepository worksRepository;


    @GetMapping(value = "/workList")
    String home(Model model, HttpServletRequest request, Principal principal, SecurityContextHolderAwareRequestWrapper securityContextHolderAwareRequestWrapper){
        String remoteAddress = "";
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(details instanceof WebAuthenticationDetails){
            remoteAddress = ((WebAuthenticationDetails) details).getRemoteAddress();
        }

        log.info("remoteAddress : "+ remoteAddress);

        log.info("request : "+request);
        Map<String, String> result = new HashMap<String, String>();
        Enumeration headerNames  = securityContextHolderAwareRequestWrapper.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String key = (String)headerNames.nextElement();
            String valsue = request.getHeader("key");
            result.put(key, valsue);
        }

        log.info("result Header : "+result);

        log.info("user : "+principal.getName());

        model.addAttribute("workList",worksRepository.findAll());
        log.debug("[length][{}]", worksRepository.findAll().size());
        log.debug(String.valueOf(worksRepository.findAll()));

        String remoteAddr = request.getHeader("X-Forwarded-For");
        if(StringUtils.isEmpty(remoteAddr)){
            remoteAddr = request.getRemoteAddr();
        }

        log.info("remote Addr " + remoteAddr);

        return "home";
    }


    @PostMapping("/addWork")
    String addWork(@ModelAttribute Work work){
        log.debug("[add work requestBody][{}]",work);
        if(!StringUtils.isEmpty(work)){
            if(!StringUtils.isEmpty(work.getName()) && !StringUtils.isEmpty(work.getPassword())){
                worksRepository.save(work);
            }
        }
        return "redirect:/workList";
    }

    @GetMapping("/deleteWork")
    String removeWork(@RequestParam(value = "id") int delectId, HttpServletRequest request){

        log.info("Delete Remote Address IP : "+request.getRemoteAddr());
        log.debug("[delectId][{}]", delectId);
        worksRepository.delete(delectId);

        return "redirect:/workList";
    }

    @PostMapping("/updateWorkItem")
    public String updateWorkItem(@ModelAttribute Work work){
        log.debug("[update work requestBody][{}]",work);
        if(!StringUtils.isEmpty(work)){
            Work updateWork = worksRepository.findOne(work.getId());
            updateWork.setInputValue(work.getInputValue());
            updateWork.setCode(work.getCode());
            updateWork.setSecondCode(work.getSecondCode());
            updateWork.setWorkItem(work.getWorkItem());
            worksRepository.save(updateWork);
        }
        return "redirect:/workList";
    }

}
