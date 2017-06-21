package kr.yerina.wmp.autonomousRegistration.controller;


import kr.yerina.wmp.autonomousRegistration.domain.Holiday;
import kr.yerina.wmp.autonomousRegistration.domain.Work;
import kr.yerina.wmp.autonomousRegistration.repository.HolidayRepository;
import kr.yerina.wmp.autonomousRegistration.slack.SlackNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class HolidayController {

    private HolidayRepository holidayRepository;

    private SlackNotifier slackNotifier;

    @Autowired
    public HolidayController(HolidayRepository holidayRepository, SlackNotifier slackNotifier) {
        this.holidayRepository = holidayRepository;
        this.slackNotifier = slackNotifier;
    }

    @GetMapping(value = "/holiday")
    String holiday(Model model){
        model.addAttribute("holidayList",holidayRepository.findAll());
        log.debug("[holidayRepository length][{}]", holidayRepository.findAll().size());
        log.debug(String.valueOf(holidayRepository.findAll()));
        return "holiday";
    }

    @PostMapping("/addHoliday")
    String addWork(@ModelAttribute Holiday holiday){
        log.debug("[add holiday requestBody][{}]", holiday);
        if(!StringUtils.isEmpty(holiday)){
            holidayRepository.save(holiday);

            SlackNotifier.SlackMessageAttachement slackMessageAttachement = new SlackNotifier.SlackMessageAttachement();
            StringBuilder slackMessage = new StringBuilder();
            slackMessage.append("["+holiday.getHolidayDate()+"]이");
            slackMessage.append("등록 되었습니다.\n");
            slackMessageAttachement.setText(slackMessage.toString());
            slackNotifier.notify(SlackNotifier.SlackTarget.CH_INCOMING, slackMessageAttachement);
        }


        return "redirect:/holiday";
    }

    @GetMapping("/deleteHoliday")
    String removeHoliday(@RequestParam(value = "id") int delectId,  HttpServletRequest request){
        log.debug("[deleteId][{}]", delectId);
        holidayRepository.delete(delectId);
        SlackNotifier.SlackMessageAttachement slackMessageAttachement = new SlackNotifier.SlackMessageAttachement();
        StringBuilder slackMessage = new StringBuilder();
        slackMessage.append("["+request.getRemoteAddr()+"]에서\n");
        slackMessage.append("휴일("+delectId +")를 삭제 하였습니다.\n");
        slackMessageAttachement.setText(slackMessage.toString());
        slackNotifier.notify(SlackNotifier.SlackTarget.CH_INCOMING, slackMessageAttachement);

        return "redirect:/holiday";
    }

}
