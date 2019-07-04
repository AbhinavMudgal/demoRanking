package RankingService.Entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.List;

public class RankedHotels {
    private HashMap<String,Double> scoremap;
    List<String> hotelids;

    @JsonAnyGetter
    public HashMap<String, Double> getScoremap() {
        return scoremap;
    }

    public void setScoremap(HashMap<String, Double> scoremap) {
        this.scoremap = scoremap;
    }

    public List<String> getHotelids() {
        return hotelids;
    }

    public void setHotelids(List<String> hotelids) {
        this.hotelids = hotelids;
    }

    public RankedHotels(HashMap<String, Double> scoremap, List<String> hotelids) {
        this.scoremap = scoremap;
        this.hotelids = hotelids;
    }

    public RankedHotels() {
    }
}
