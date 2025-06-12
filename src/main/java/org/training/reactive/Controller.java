package org.training.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @Autowired
    Service service;

    @GetMapping("/honey")
    public String index() {
        return "Honey";
    }

    @GetMapping("/random")
    public ResponseEntity<Mono<Integer>> randomNumber() {
        return new ResponseEntity<>(service.getRandomNumber(), HttpStatus.OK);
    }


}
