package RankingService.Services;

import RankingService.Configurations.ElasticConfiguration;
import RankingService.Entity.HotelRatings;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import java.io.IOException;
import java.util.List;

public class ElasticSearchService {
    public String fetchFromES(Integer id) throws IOException {
        ElasticConfiguration elasticConfig=new ElasticConfiguration();
        JestClient client = elasticConfig.jestClient();
        String search= String.format("{ \"query\":{\"term\" :{\"hotel_id\":\"%s\"}}}", id.toString());
        SearchResult result=client.execute(new Search.Builder(search).build());
        List<SearchResult.Hit<HotelRatings, Void>> hits = result.getHits(HotelRatings.class);
        if (hits.size()==0){
            return "";
        }
        for (SearchResult.Hit<HotelRatings, Void> hit: hits) {
            HotelRatings hotelRatingsOutput = hit.source;
            String rating=hotelRatingsOutput.getRating_on_scale_of_5();
            return rating;
            //System.out.println("HIIII"+rating+" "+hotelRatingsOutput.getHotel_id());
        }
        return "";
    }
}
