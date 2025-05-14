package dk.sdu.mmmi.cbse.main.microserviceManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ServiceLoader;

public class ScoreClient {
    private final RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreClient.class);
    private static final String SCORE_URL = "http://localhost:8080/score";

    public ScoreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendScore(int points) {
        Integer score = restTemplate.getForObject(SCORE_URL + "?point=" + points, Integer.class);
        LOGGER.info(score.toString());
    }
}