package com.team8.volunteerworkproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RedissonConcurrencyTest {

    @Autowired
    private RedissonClient redissonClient;

    private final int ENROLLMENT_USERS = 10;
    private final int ATTEND_NUM = 100;//동시 실행 반복 횟수
    private final int EXPECTED_RESULT = ENROLLMENT_USERS * ATTEND_NUM;

    @Test
    public void test_concurrent_access() throws InterruptedException {
        RLock lock = redissonClient.getLock("test-lock");

        ExecutorService executorService = Executors.newFixedThreadPool(ENROLLMENT_USERS);
        int[] counter = new int[]{0};

        for (int i = 0; i < ENROLLMENT_USERS; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < ATTEND_NUM; j++) {
                    lock.lock();
                    try {
                        counter[0]++;
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        assertEquals(EXPECTED_RESULT, counter[0]);
    }
    //위 코드에서는 Redisson의 RLock을 사용하여 락을 걸고, 여러 개의 쓰레드가 동시에 값을 증가시키는 작업을 수행.
    // 이 코드를 실행하면 모든 쓰레드가 동시에 실행되어 계산이 중복되지 않게 됩니다.
    // 따라서, 예상되는 결과값은 ENROLLMENT_USERS * ATTEND_NUM 인 1000이 되어야 합니다.
    // 이 값을 assertEquals() 메서드를 사용하여 검증합니다.
    //참고로 Redisson을 사용하여 동시성 제어를 하기 위해서는 Redis 서버가 먼저 필요합니다.
    // 로컬에서 Redis를 설치하고 실행하거나, 무료로 제공되는 Redis 클라우드 서비스를 이용하면 됩니다.
}