package RankingService.Controller;

import RankingService.Entity.HotelFeatures;
import RankingService.Handler.RankHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/oyorank")
public class RankController {
    RankHandler rankHandler=new RankHandler();
    @RequestMapping( method = RequestMethod.POST)
    public String rankingService(@RequestBody List<HotelFeatures> inputHotels) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String rankAsString = objectMapper.writeValueAsString(rankHandler.handler(inputHotels));
        return rankAsString;
    }
}
