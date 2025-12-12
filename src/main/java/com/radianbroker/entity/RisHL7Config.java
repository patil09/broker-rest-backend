package com.radianbroker.entity;


import com.radianbroker.audit.PacsConfigConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "ris_hl7_configs")
public class RisHL7Config {
    @Id
    @Column(name = "ris_id")
    private Long risId;

    @Column(name = "hl7_receive_port", nullable = true)
    private Integer hl7ReceivePort;

    @Column(name = "hl7_send_host", nullable = true)
    private String hl7SendHost;

    @Column(name = "hl7_send_port", nullable = true)
    private Integer hl7SendPort;

    @Convert(converter = PacsConfigConverter.class)
    @Column(name = "pacs_config", columnDefinition = "json")
    private PacsConfig pacsConfig;

    @Column(name = "hl7_send_host2", nullable = true)
    private String hl7SendHost2;

    @Column(name = "hl7_send_port2", nullable = true)
    private Integer hl7SendPort2;

    @Column(name = "jms_route", nullable = true)
    private String jmsRoute;

    public Long getRisId() {
        return risId;
    }

    public void setRisId(Long risId) {
        this.risId = risId;
    }

    public Integer getHl7ReceivePort() {
        return hl7ReceivePort;
    }

    public void setHl7ReceivePort(Integer hl7ReceivePort) {
        this.hl7ReceivePort = hl7ReceivePort;
    }

    public String getHl7SendHost() {
        return hl7SendHost;
    }

    public void setHl7SendHost(String hl7SendHost) {
        this.hl7SendHost = hl7SendHost;
    }

    public Integer getHl7SendPort() {
        return hl7SendPort;
    }

    public void setHl7SendPort(Integer hl7SendPort) {
        this.hl7SendPort = hl7SendPort;
    }

    public PacsConfig getPacsConfig() {
        return pacsConfig;
    }

    public void setPacsConfig(PacsConfig pacsConfig) {
        this.pacsConfig = pacsConfig;
    }

    public String getHl7SendHost2() {
        return hl7SendHost2;
    }

    public void setHl7SendHost2(String hl7SendHost2) {
        this.hl7SendHost2 = hl7SendHost2;
    }

    public Integer getHl7SendPort2() {
        return hl7SendPort2;
    }

    public void setHl7SendPort2(Integer hl7SendPort2) {
        this.hl7SendPort2 = hl7SendPort2;
    }

    public String getJmsRoute() {
        return jmsRoute;
    }

    public void setJmsRoute(String jmsRoute) {
        this.jmsRoute = jmsRoute;
    }

}