package kr.yerina.wmp.autonomousRegistration.properties;
import ch.qos.logback.classic.Level;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "log")
@Data
public class LogConfig {

    private Level level;

    private Slack slack;

    private Database database;

    public static class Slack {
        private boolean enabled;
        private String webHookUrl;
        private String channel;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getWebHookUrl() {
            return webHookUrl;
        }

        public void setWebHookUrl(String webHookUrl) {
            this.webHookUrl = webHookUrl;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }
    }

    public static class Database {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }


}
