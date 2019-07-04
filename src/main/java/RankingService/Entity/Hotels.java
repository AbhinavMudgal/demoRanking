package RankingService.Entity;

import java.util.ArrayList;
import java.util.List;

public class Hotels {
    List<String> hotel_ids= new ArrayList<String>();

    public List<String> getHotel_ids() {
        return hotel_ids;
    }

    public void setHotel_ids(List<String> hotel_ids) {
        this.hotel_ids = hotel_ids;
    }
    public Hotels()
    {

    }
    public Hotels(List<String> hotel_ids) {
        this.hotel_ids = hotel_ids;
    }
}
