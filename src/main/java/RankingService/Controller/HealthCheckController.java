package RankingService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {


    @RequestMapping(value = "/ping")
    @ResponseBody
    public String healthCheck() {
        return HttpStatus.OK.getReasonPhrase();
    }
}

