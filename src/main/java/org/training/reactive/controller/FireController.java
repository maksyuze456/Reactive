package org.training.reactive.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.training.reactive.dto.FireRequestDTO;
import org.training.reactive.dto.FireResponseDTO;
import org.training.reactive.service.FireService;

@RestController
public class FireController {

    @Autowired
    FireService fireService;

    @PostMapping("/fires")
    public ResponseEntity<FireResponseDTO> addFire(@RequestBody FireRequestDTO fireRequestDTO) {
        return new ResponseEntity<>(fireService.addFire(fireRequestDTO), HttpStatus.CREATED);
    }

}
