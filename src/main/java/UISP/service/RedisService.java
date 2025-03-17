package UISP.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveData(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
