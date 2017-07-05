package kr.yerina.wmp.autonomousRegistration.service.inf;

import kr.yerina.wmp.autonomousRegistration.properties.LogConfig;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SlackSendService {

    @Autowired
    private LogConfig logConfig;

    public void sendSlack(String title, String pushMessage){
        SlackApi slackApi = new SlackApi(logConfig.getSlack().getWebHookUrl());

        List<SlackField> fields = new ArrayList<>();

        SlackField message = new SlackField();
        message.setTitle(title);
        message.setValue(pushMessage);
        message.setShorten(false);
        fields.add(message);

        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setFallback("Information");
        slackAttachment.setColor("good");
        slackAttachment.setFields(fields);

        SlackMessage slackMessage = new SlackMessage("");
        slackMessage.setChannel("#" + logConfig.getSlack().getChannel());
        slackMessage.setUsername("AUTO_WMP");

        slackMessage.setAttachments(Collections.singletonList(slackAttachment));

        slackApi.call(slackMessage);
    }

}
