package com.pineapple.pineapplelogistics.utils;

import com.pineapple.pineapplelogistics.exception.SnowflakeException;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @Author:Pineapple
 * @Description:
 * @Date: 19:04 2023/4/23
 * @Modifier by:
 */

@Slf4j
public class SnowflakeUtils {
    private final long twepoch;
    private final long workerIdBits = 10L;
    private final long maxWorkerId = ~(-1L << workerIdBits);//最大能够分配的workerid =1023
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits;
    private final long sequenceMask = ~(-1L << sequenceBits);
    private long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;
    private static final Random RANDOM = new Random();




    public SnowflakeUtils(long twepoch,long workerId) {
        this.twepoch = twepoch;
        this.workerId = workerId;
    }

    public synchronized long get() {
        //获取时间戳
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            long offset = lastTimestamp - timestamp;
            if (offset <= 5) {
                try {
                    wait(offset << 1);
                    timestamp = timeGen();
                    if (timestamp < lastTimestamp) {
                        throw new SnowflakeException("time stamp error");
                    }
                } catch (InterruptedException e) {
                    throw new SnowflakeException("interrupt error");

                }
            } else {
                throw new SnowflakeException("exception error");
            }
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                //seq 为0的时候表示是下一毫秒时间开始对seq做随机
                sequence = RANDOM.nextInt(100);
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //如果是新的ms开始
            sequence = RANDOM.nextInt(100);
        }
        lastTimestamp = timestamp;
        long id = ((timestamp - twepoch) << timestampLeftShift) | (workerId << workerIdShift) | sequence;
        return  id;

    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public long getWorkerId() {
        return workerId;
    }
}
