package RankingService.Services;

import RankingService.Entity.HotelFeatures;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;


//@restcontroller
public class SageMakerService {

    public HashMap<String,Double> getScores(List<HotelFeatures> inputHotels)
    {
        RestTemplate restTemplate =new RestTemplate();
        HashMap<String,Double> response=restTemplate.postForObject(
                "https://ate89taaog.execute-api.ap-southeast-1.amazonaws.com/test/predictbreastcancer",
                inputHotels,
                HashMap.class);
        return response;
    }
}
