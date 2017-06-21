package kr.yerina.wmp.autonomousRegistration.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class WmpServiceConfigs {
    @Getter @Setter
    private String desc;

}
