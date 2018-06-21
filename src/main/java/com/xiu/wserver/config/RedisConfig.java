package com.xiu.wserver.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.time.Duration;


/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/5/16 14:17
 * @Description 类描述:  redis配置
 */

@Configuration
public class RedisConfig {

    /**
     * @method 方法名: redisStandaloneConfiguration
     * @Decription 方法描述: redis单机连接配置
     * lettuceConnectionFactory的setHost方法已废弃，故添加此配置
     *
     * @params 传入参数:[]
     * @return 返回值类型:org.springframework.data.redis.connection.RedisStandaloneConfiguration
     * @throws
     */

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("www.likepeng.cn");
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setPassword(RedisPassword.of("redis"));
        return redisStandaloneConfiguration;
    }

    /**
     * @method 方法名: lettuceConnectionFactory
     * @Decription 方法描述: 连接工厂
     *
     * @params 传入参数:[]
     * @return 返回值类型:org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
     * @throws
     */

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(50000))
                .shutdownTimeout(Duration.ofSeconds(50000))
                .build();
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration(),clientConfig);
        return connectionFactory;
    }


    /**
     * @method 方法名: redisTemplate
     * @Decription 方法描述: redis模板
     *
     * @params 传入参数:[]
     * @return 返回值类型:org.springframework.data.redis.core.RedisTemplate
     * @throws
     */

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        RedisSerializer<String> keySerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;

//        redisTemplate.setValueSerializer(redisSerializer);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return  redisTemplate;
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().
                entryTtl(Duration.ofSeconds(50000))
                .disableCachingNullValues();
        return redisCacheConfiguration;
    }

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager cacheManager = RedisCacheManager.builder(lettuceConnectionFactory()).cacheDefaults(redisCacheConfiguration()).build();
        return cacheManager;
    }


        /**
         * @method 方法名: valueOperations
         * @Decription 方法描述: 针对redis String类型的操作模板
         *
         * @params 传入参数:[]
         * @return 返回值类型:org.springframework.data.redis.core.ValueOperations<java.lang.String,java.lang.String>
         * @throws
         */

    @Bean
    public ValueOperations<String,Object> valueOperations(){

        return redisTemplate().opsForValue();
    }


    /**
     * @method 方法名: hashOperations
     * @Decription 方法描述: 针对redis Hash类型的模板操作
     *
     * @params 传入参数:[]
     * @return 返回值类型:org.springframework.data.redis.core.HashOperations<java.lang.String,byte[],byte[]>
     * @throws
     */

    @Bean
    public HashOperations<String, String, Object> hashOperations(){
        return redisTemplate().opsForHash();
    }


    /**
     * @method 方法名: listOperations
     * @Decription 方法描述: 针对redis List类型的模板操作
     *
     * @params 传入参数:[]
     * @return 返回值类型:org.springframework.data.redis.core.ListOperations<java.lang.String,java.lang.String>
     * @throws
     */

    @Bean
    public ListOperations<String,String> listOperations(){
        return redisTemplate().opsForList();
    }


    /**
     * @method 方法名: setOperations
     * @Decription 方法描述: 针对redis Set类型的模板操作
     *
     * @params 传入参数:[]
     * @return 返回值类型:org.springframework.data.redis.core.SetOperations<java.lang.String,java.lang.String>
     * @throws
     */

    @Bean
    public SetOperations<String,String> setOperations(){

        return redisTemplate().opsForSet();
    }


    /**
     * @method 方法名: zSetOperations
     * @Decription 方法描述: 针对redis ZSet类型的模板操作
     *
     * @params 传入参数:[]
     * @return 返回值类型:org.springframework.data.redis.core.ZSetOperations<java.lang.String,java.lang.String>
     * @throws
     */

    @Bean
    public ZSetOperations<String,String> zSetOperations(){
        return redisTemplate().opsForZSet();
    }

}
