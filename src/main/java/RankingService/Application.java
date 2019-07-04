package RankingService;


import RankingService.ScheduledJobs.RatingCronJob;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
       // RatingCronJob job=new RatingCronJob();
    }
}
