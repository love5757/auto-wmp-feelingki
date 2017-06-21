package kr.yerina.wmp.autonomousRegistration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class WmpServiceProperties {
    @Getter @Setter
    private String desc;

}
