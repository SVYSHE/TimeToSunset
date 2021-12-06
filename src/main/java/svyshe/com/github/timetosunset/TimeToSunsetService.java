package svyshe.com.github.timetosunset;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class TimeToSunsetService {
    private final HttpClient httpClient;

    public TimeToSunsetService() {
        httpClient = HttpClient.newHttpClient();
    }

    public TimeToSunset getSunsetTimeForCity(String city) throws IOException, InterruptedException {
        String apiKey = "&appid=76b3e29ea805b78ad53f9fcc8ec3fa44";
        String baseUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + city + apiKey))
                .header("accept", "application/json")
                .GET()
                .build();
        var httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = httpResponse.body();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(body);
        JsonNode sysNode = rootNode.get("sys");
        JsonNode sunsetNode = sysNode.get("sunset");
        String unixTimeString = sunsetNode.asText();
        long unixTime = Long.parseLong(unixTimeString);
        LocalDateTime sunsetTime = Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("Europe/Berlin")).toLocalDateTime();
        String formattedSunsetTime = sunsetTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        TimeToSunset data = new TimeToSunset();
        data.setSunset(formattedSunsetTime);
        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        data.setNow(formattedNow);
        long timeToSunset = Duration.between(now, sunsetTime).toMillis();
        if (timeToSunset <= 0){
            data.setTimeToSunset("00:00:00");
        }else{
            String formattedTimeToSunset = DurationFormatUtils.formatDuration(timeToSunset, "HH:mm:ss", true);
            data.setTimeToSunset(formattedTimeToSunset);
        }
        return data;
    }
}
