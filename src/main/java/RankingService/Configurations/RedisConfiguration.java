package RankingService.Configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

@Configuration
public class RedisConfiguration {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        RedisNode node = new RedisNode("oyoautocomplete-stag.h2xiyd.clustercfg.apse1.cache.amazonaws.com", 6379);
        clusterConfiguration.setClusterNodes(Arrays.asList(node));
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(clusterConfiguration);
        return connectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate strRedisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory());
        return redisTemplate;
    }
}