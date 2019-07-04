package RankingService.Services;

import RankingService.Configurations.RedisConfiguration;
import RankingService.Entity.HotelRatings;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

public class RedisService {
    public String fetchFromRedis(Integer id) {
        //Connection Open
        try {
            ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
                    RedisConfiguration.class);
            StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("strRedisTemplate");
            //STATUS----System.out.println(redisTemplate.getConnectionFactory().getConnection().ping() != null);
            String rating=redisTemplate.opsForValue().get(id.toString());
            if (rating==null)
            {
                System.out.println("NULL RETURNED");
                ctx.close(); //Closed
                return "";
            }
            else
            {
                ctx.close(); //Closed
                return rating;
            }

        } catch (BeansException e) {
            e.printStackTrace();

        }
        return "";
    }




}
