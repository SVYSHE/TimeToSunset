package svyshe.com.github.timetosunset;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

public class TimeToSunsetProviderTest {

    @ParameterizedTest
    @ValueSource(strings = {"bonn", "köln", "neunkirchen-seelscheid", "london", "euskirchen"})
    void testRequest(String city) throws IOException, InterruptedException {
        TimeToSunsetService provider = new TimeToSunsetService();
        TimeToSunset sunsetTime = provider.getSunsetTimeForCity(city);
        System.out.println("CITY: " + city + " | SUNSET: " + sunsetTime.getSunset() + " | TIME TO SUNSET: " + sunsetTime.getTimeToSunset());

    }
}
