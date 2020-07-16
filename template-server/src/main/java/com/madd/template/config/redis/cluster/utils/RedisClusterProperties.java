package com.madd.template.config.redis.cluster.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis 集群版对应配置连接池JedisPool
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterProperties {
    private int expireSeconds;
    private String nodes;
    private int commandTimeout;

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    @Override
    public String toString() {
        return "RedisClusterProperties{" +
                "expireSeconds=" + expireSeconds +
                ", nodes='" + nodes + '\'' +
                ", commandTimeout=" + commandTimeout +
                '}';
    }
}