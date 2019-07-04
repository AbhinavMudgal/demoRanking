package RankingService.Entity;

public class HotelFeatures {
    private int hotelId;
    private float rating_mean;
    private float bookingRate;
    private float fprice;
    private float discount_per;

    public HotelFeatures(int hotelId, float rating_mean, float bookingRate, float fprice, float discount_per) {
        this.hotelId = hotelId;
        this.rating_mean = rating_mean;
        this.bookingRate = bookingRate;
        this.fprice = fprice;
        this.discount_per = discount_per;
    }

    public HotelFeatures() {
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public float getRating_mean() {
        return rating_mean;
    }

    public void setRating_mean(float rating_mean) {
        this.rating_mean = rating_mean;
    }

    public float getBookingRate() {
        return bookingRate;
    }

    public void setBookingRate(float bookingRate) {
        this.bookingRate = bookingRate;
    }

    public float getFprice() {
        return fprice;
    }

    public void setFprice(float fprice) {
        this.fprice = fprice;
    }

    public float getDiscount_per() {
        return discount_per;
    }

    public void setDiscount_per(float discount_per) {
        this.discount_per = discount_per;
    }
}
