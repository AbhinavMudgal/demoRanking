package RankingService.Handler;

import RankingService.Entity.HotelFeatures;
import RankingService.Services.ElasticSearchService;
import RankingService.Services.RedisService;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.List;

public class RatingChecker {
    public List<HotelFeatures> update(List<HotelFeatures> hotelFeaturesList) throws IOException {
        RedisService redisService=new RedisService();
        ElasticSearchService elasticSearchService=new ElasticSearchService();
        for (int i=0;i<hotelFeaturesList.size();i++)
        {
            int id=hotelFeaturesList.get(i).getHotelId();
            float rating=hotelFeaturesList.get(i).getRating_mean();
            if (rating==0.0)
            {
                String fetchedRating=redisService.fetchFromRedis(id);
                if (fetchedRating=="")
                {
                    System.out.println("NOT AVAILABLE In Redis");
                    fetchedRating=elasticSearchService.fetchFromES(id);

                }
                if (fetchedRating=="")
                {
                    System.out.println("NOT AVAILABLE in ES as well");
                    continue;
                }

                    rating=Float.parseFloat(fetchedRating);
                    System.out.println(rating+" fetched");
                    hotelFeaturesList.get(i).setRating_mean(rating);
                    System.out.println(hotelFeaturesList.get(i).getRating_mean()+" updated rating value in object");

            }
        }
        return hotelFeaturesList;
    }
}
