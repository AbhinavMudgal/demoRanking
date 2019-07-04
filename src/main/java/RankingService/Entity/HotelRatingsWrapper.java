package RankingService.Entity;

import java.util.HashMap;
import java.util.Map;

public class HotelRatingsWrapper {
    Map<String, HotelRatings> data = new HashMap<String,HotelRatings>();

    public HotelRatingsWrapper(Map<String, HotelRatings> data) {
        this.data = data;
    }

    public Map<String, HotelRatings> getData() {
        return data;
    }

    public void setData(Map<String, HotelRatings> data) {
        this.data = data;
    }
}
