package com.example.demo.modules.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "systemconfig")
public class SystemConfig {
    private String redisAddr;
    private boolean redisOpen;
    private String zookeeperAddr;
    private String aliypayAddr;

    public String getRedisAddr() {
        return redisAddr;
    }

    public void setRedisAddr(String redisAddr) {
        this.redisAddr = redisAddr;
    }

    public boolean isRedisOpen() {
        return redisOpen;
    }

    public void setRedisOpen(boolean redisOpen) {
        this.redisOpen = redisOpen;
    }

    public String getZookeeperAddr() {
        return zookeeperAddr;
    }

    public void setZookeeperAddr(String zookeeperAddr) {
        this.zookeeperAddr = zookeeperAddr;
    }

    public String getAliypayAddr() {
        return aliypayAddr;
    }

    public void setAliypayAddr(String aliypayAddr) {
        this.aliypayAddr = aliypayAddr;
    }

    @Override
    public String toString() {
        return "SystemConfig{" +
                "redisAddr='" + redisAddr + '\'' +
                ", redisOpen=" + redisOpen +
                ", zookeeperAddr='" + zookeeperAddr + '\'' +
                ", aliypayAddr='" + aliypayAddr + '\'' +
                '}';
    }
}
