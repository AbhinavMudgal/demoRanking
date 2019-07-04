package RankingService.Entity;



public class HotelRatings {
    String hotel_id;
    String rating_on_scale_of_5;

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getRating_on_scale_of_5() {
        return rating_on_scale_of_5;
    }

    public void setRating_on_scale_of_5(String rating_on_scale_of_5) {
        this.rating_on_scale_of_5 = rating_on_scale_of_5;
    }

    public HotelRatings(String hotel_id, String rating_on_scale_of_5) {
        this.hotel_id = hotel_id;
        this.rating_on_scale_of_5 = rating_on_scale_of_5;
    }
}
