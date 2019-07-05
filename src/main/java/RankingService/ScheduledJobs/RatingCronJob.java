package RankingService.ScheduledJobs;


import RankingService.Configurations.ElasticConfiguration;
import RankingService.Configurations.RedisConfiguration;
import RankingService.Entity.HotelRatings;
import RankingService.Entity.HotelRatingsWrapper;
import RankingService.Entity.Hotels;
import com.google.gson.Gson;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Configuration
//@EnableScheduling
public class RatingCronJob {

    private List<String> hotelIds;

    public RatingCronJob() {
        hotelIds=new ArrayList<String>();
        hotelIds.add("541");
        hotelIds.add("542");
        hotelIds.add("913");
        hotelIds.add("914");
        hotelIds.add("917");
        hotelIds.add("60988");
        hotelIds.add("62695");
        hotelIds.add("12342");
    }

    //@Scheduled(fixedRate = 500000)  // @Scheduled(cron="*/5 * * * * ?") every 5 sec  (0 0 12 ? * * *) at 12 pm everyday fixedRate = 500000 at every 500 sec

    public void execute() throws IOException {
        System.out.println("I schedule after 500 seconds");

        HotelRatingsWrapper response=fetchFromRatingService();
        feedRedis(response);
        feedElastic(response);
    }

    private void feedElastic(HotelRatingsWrapper parsedResponse) throws IOException {
        ElasticConfiguration elasticConfig=new ElasticConfiguration();
        JestClient client = elasticConfig.jestClient();
        boolean indexExists = elasticConfig.jestClient().execute(new IndicesExists.Builder("oyohotels").build()).isSucceeded();
        if (indexExists) {
            client.execute(new DeleteIndex.Builder("oyohotels").build()); //avoiding duplicacy

        }
        client.execute(new CreateIndex.Builder("oyohotels").build());

        Map<String, HotelRatings> hotelMap = parsedResponse.getData();
        for (Map.Entry mapElement : hotelMap.entrySet())
        {
            HotelRatings currentHotelRating = (HotelRatings) mapElement.getValue();
            client.execute(new Index.Builder(currentHotelRating).index("oyohotels").type("rating").build());
            System.out.println(currentHotelRating.getHotel_id()+" added to oyohotels index" );
        }

    }

    private void feedRedis(HotelRatingsWrapper parsedResponse) {

        //Connection Open
        try {
            ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
                    RedisConfiguration.class);
            StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("strRedisTemplate");
            //STATUS----System.out.println(redisTemplate.getConnectionFactory().getConnection().ping() != null);
            //iterating
            Map<String, HotelRatings> hotelMap = parsedResponse.getData();
            for (Map.Entry mapElement : hotelMap.entrySet()) {
                String key = (String) mapElement.getKey();
                HotelRatings object = (HotelRatings) mapElement.getValue();
                String value = object.getRating_on_scale_of_5();
                redisTemplate.opsForValue().set(key, value);
                System.out.println("REDIS UPDATED with  " + key + " : " + value);
            }
            ctx.close(); //Closed
        } catch (BeansException e) {
            e.printStackTrace();

        }
    }

    public HotelRatingsWrapper fetchFromRatingService()
    {

        //List of hotels to be sent asking for their rating
        Hotels hotels=new Hotels(hotelIds);
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.postForObject("http://ratings.ap-southeast-1.elasticbeanstalk.com/ratings/overall",hotels, String.class);
        //System.out.println(response);
        Gson g=new Gson();
        HotelRatingsWrapper parsedResponse=g.fromJson(response,HotelRatingsWrapper.class);

        //System.out.println(parsedResponse.getData().get("60988").getHotel_id());
        return parsedResponse;
    }

}
