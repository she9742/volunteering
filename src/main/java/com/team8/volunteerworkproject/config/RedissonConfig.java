package com.team8.volunteerworkproject.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");
        return Redisson.create(config);
    }
}
//단일 Redis 서버 인스턴스를 구성하는 방법을 RedissonClient 사용하여 빈을 생성했습니다.
// useSingleServer 이 setAddress 메서드는 Redis 서버의 주소를 지정합니다.
// destroyMethod 주석 의 속성은 Bean @Bean 이 소멸될 때 호출할 메서드의 이름을 지정합니다.
// 이 경우 shutdown 인스턴스의 메서드 입니다
