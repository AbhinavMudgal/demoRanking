package RankingService.Handler;

import RankingService.Entity.HotelFeatures;
import RankingService.Entity.RankedHotels;
import RankingService.Services.SageMakerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankHandler {

    public RankedHotels handler(List<HotelFeatures> inputHotels) throws IOException {
        RatingChecker ratingChecker=new RatingChecker();
        inputHotels=ratingChecker.update(inputHotels);
        //Fetch CHECK for (int i=0;i<inputHotels.size();i++) { System.out.println(inputHotels.get(i).getHotelId()+" "+inputHotels.get(i).getRating_mean()); }
        SageMakerService sageMakerService=new SageMakerService();
        HashMap<String,Double> scores=sageMakerService.getScores(inputHotels);
        return sorter(scores);

    }

    private RankedHotels sorter(HashMap<String, Double> scores) {
        List <Double> hotelscores=new ArrayList<>();
        List <String> hotelids = new ArrayList<>();
        for (Map.Entry<String,Double> entry : scores.entrySet())
        {
            hotelscores.add(entry.getValue());
            hotelids.add(entry.getKey());
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
        hotelids=mySort(hotelscores,hotelids);
        System.out.println(hotelids);
        RankedHotels rankedHotels=new RankedHotels(scores,hotelids);
        return rankedHotels;
    }


    public List<String> mySort(List<Double> scores,List<String> ids)
    {
        for (int i=0;i<scores.size()-1;i++)
        {
            for (int j=i+1;j<scores.size();j++)
            {
                if (scores.get(i)<scores.get(j))
                {
                    double temp1=scores.get(i);
                    scores.set(i,scores.get(j));
                    scores.set(j,temp1);
                    String temp2=ids.get(i);
                    ids.set(i,ids.get(j));
                    ids.set(j,temp2);
                }
            }
        }
        return ids;
    }

}
