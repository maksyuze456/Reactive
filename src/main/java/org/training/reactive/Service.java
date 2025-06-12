package org.training.reactive;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
public class Service {

    private final WebClient randomNumberWebClient;

    public Service() {
        this.randomNumberWebClient = WebClient.create("http://www.randomnumberapi.com");
    }

    public Mono<Integer> getRandomNumber() {

        return randomNumberWebClient.get()
                .uri("/api/v1.0/random?min=1&max=10&count=1")
                .retrieve()
                .bodyToMono(Integer[].class)
                .map(randomNumbers -> randomNumbers[0]);
    }

}
