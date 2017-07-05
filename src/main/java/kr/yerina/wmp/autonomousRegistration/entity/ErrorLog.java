package kr.yerina.wmp.autonomousRegistration.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "ERROR_LOGS")
@Data
public class ErrorLog {

    @Id
    @Column(name = "ID", precision = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PHASE", length = 10)
    private String phase;

    @Column(name = "SYSTEM", length = 50)
    private String system;

    @Column(name = "LOGGER_NAME", length = 300)
    private String loggerName;

    @Column(name = "SERVER_NAME", length = 50)
    private String serverName;

    @Column(name = "HOST_NAME", length = 50)
    private String hostName;

    @Column(name = "PATH", length = 2048)
    private String path;

    @Column(name = "MESSAGE", columnDefinition = "TEXT")
    private String message;

    @Column(name = "TRACE", columnDefinition = "TEXT")
    private String trace;

    @Column(name = "ERROR_DATETIME")
    private LocalDateTime errorDatetime = LocalDateTime.now();

    @Column(name = "ALERT_YN", length = 1)
    private String alertYn = "N";

    @Column(name = "HEADER_MAP", columnDefinition = "TEXT")
    private String headerMap;

    @Column(name = "PARAMETER_MAP", columnDefinition = "TEXT")
    private String parameterMap;

    @Column(name = "USER_INFO", columnDefinition = "TEXT")
    private String userInfo;

    @Column(name = "AGENT_DETAIL", columnDefinition = "TEXT")
    private String agentDetail;

}