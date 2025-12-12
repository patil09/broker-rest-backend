package com.radianbroker.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "ris_rabbitmq_config")
public class RisRabbitMQConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ris_id")
    private Long risId;

    @Column(name = "ris_code", nullable = false, unique = true)
    private String risCode;

    @Column(name = "broker_host", nullable = false)
    private String brokerHost;

    @Column(name = "broker_port", nullable = false)
    private int brokerPort;

    @Column(name = "broker_user_name", nullable = false)
    private String brokerUsername;

    @Column(name = "broker_password", nullable = false)
    private String brokerPassword;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default false")
    private boolean active = false;

    // Getters & Setters
    public Long getRisId() {
        return risId;
    }

    public void setRisId(Long risId) {
        this.risId = risId;
    }

    public String getRisCode() {
        return risCode;
    }

    public void setRisCode(String risCode) {
        this.risCode = risCode;
    }

    public String getBrokerHost() {
        return brokerHost;
    }

    public void setBrokerHost(String brokerHost) {
        this.brokerHost = brokerHost;
    }

    public int getBrokerPort() {
        return brokerPort;
    }

    public void setBrokerPort(int brokerPort) {
        this.brokerPort = brokerPort;
    }

    public String getBrokerUsername() {
        return brokerUsername;
    }

    public void setBrokerUsername(String brokerUsername) {
        this.brokerUsername = brokerUsername;
    }

    public String getBrokerPassword() {
        return brokerPassword;
    }

    public void setBrokerPassword(String brokerPassword) {
        this.brokerPassword = brokerPassword;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
