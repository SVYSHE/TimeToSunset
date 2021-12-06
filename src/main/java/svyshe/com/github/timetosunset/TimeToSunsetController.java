package svyshe.com.github.timetosunset;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/timetosunset")
public class TimeToSunsetController {

    private final TimeToSunsetService timeToSunsetService;

    public TimeToSunsetController() {
        timeToSunsetService = new TimeToSunsetService();
    }

    @GetMapping(value = "{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TimeToSunset findTimeToSunset(@PathVariable String cityName) throws IOException, InterruptedException {
        return timeToSunsetService.getSunsetTimeForCity(cityName);
    }

}
