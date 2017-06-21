package kr.yerina.wmp.autonomousRegistration.controller;


import kr.yerina.wmp.autonomousRegistration.properties.WmpServiceProperties;
import kr.yerina.wmp.autonomousRegistration.domain.Work;
import kr.yerina.wmp.autonomousRegistration.repository.WorksRepository;
import kr.yerina.wmp.autonomousRegistration.slack.SlackNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by philip on 2017-05-31.
 */
@Slf4j
@Controller
@EnableConfigurationProperties(WmpServiceProperties.class)
@RefreshScope
public class WmpHomeController {

    @Autowired
    private WorksRepository worksRepository;

    @Autowired
    private SlackNotifier slackNotifier;

    @Autowired
    private WmpServiceProperties wmpServiceProperties;


    @GetMapping(value = "/")
    String home(Model model){
        model.addAttribute("workList",worksRepository.findAll());
        log.debug("[length][{}]", worksRepository.findAll().size());
        log.debug(String.valueOf(worksRepository.findAll()));
        return "home";
    }


    @PostMapping("/addWork")
    String addWork(@ModelAttribute Work work){
        log.debug("[add work requestBody][{}]",work);
        if(!StringUtils.isEmpty(work)){
            if(!StringUtils.isEmpty(work.getName()) && !StringUtils.isEmpty(work.getPassword())){
                worksRepository.save(work);

                SlackNotifier.SlackMessageAttachement slackMessageAttachement = new SlackNotifier.SlackMessageAttachement();
                StringBuilder slackMessage = new StringBuilder();
                slackMessage.append("["+work.getName()+"]이");
                slackMessage.append("등록 되었습니다.\n");
                slackMessageAttachement.setText(slackMessage.toString());
                slackNotifier.notify(SlackNotifier.SlackTarget.CH_INCOMING, slackMessageAttachement);
            }
        }


        return "redirect:/";
    }

    @GetMapping("/deleteWork")
    String removeWork(@RequestParam(value = "id") int delectId, HttpServletRequest request){
        log.info("Delete Remote Address IP : "+request.getRemoteAddr());
        log.debug("[delectId][{}]", delectId);
        worksRepository.delete(delectId);

        SlackNotifier.SlackMessageAttachement slackMessageAttachement = new SlackNotifier.SlackMessageAttachement();
        StringBuilder slackMessage = new StringBuilder();
        slackMessage.append("["+request.getRemoteAddr()+"]에서\n");
        slackMessage.append(delectId +"를 삭제 하였습니다.\n");
        slackMessageAttachement.setText(slackMessage.toString());
        slackNotifier.notify(SlackNotifier.SlackTarget.CH_INCOMING, slackMessageAttachement);

        return "redirect:/";
    }

}
