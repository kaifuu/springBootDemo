package com.kaifuu.demo.config.redis;


import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class RedisConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private String port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private String database;
    @Value("${redis.timeout}")
    private String timeout;

    @Bean
    public RedisFactory redisFactory() {
        int _port = NumberUtils.toInt(port, 6379);
        int _timeout = NumberUtils.toInt(timeout, 10000);
        int _database = NumberUtils.toInt(database, 0);
        return new RedisFactory(host, _port, _timeout, password, _database, "");
    }

    @Bean
    public RedisWrapper redisWrapper(RedisFactory factory) {
        return new RedisWrapper(factory);
    }
}
