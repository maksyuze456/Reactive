package org.training.reactive.controller;

import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.training.reactive.dto.FireRequestDTO;
import org.training.reactive.dto.FireResponseDTO;
import org.training.reactive.dto.SirenResponseDTO;
import org.training.reactive.model.Status;
import org.training.reactive.service.FireService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class FireController {

    @Autowired
    FireService fireService;

    @PostMapping("/fires")
    public ResponseEntity<FireResponseDTO> addFire(@RequestBody FireRequestDTO fireRequestDTO) {
        FireResponseDTO addedFire = fireService.addFire(fireRequestDTO);
        return new ResponseEntity<>(addedFire, HttpStatus.CREATED);
    }

    @GetMapping("/fires")
    public ResponseEntity<List<FireResponseDTO>> getAllActiveFires(@RequestParam("status") Status status) {

        return new ResponseEntity<>(fireService.getAllActiveFires(status), HttpStatus.OK);
    }

    @PutMapping("/fires/{id}/closure")
    public ResponseEntity<FireResponseDTO> disableFire(@PathVariable long id) {
        try {
            FireResponseDTO updatedFireResponseDTO = fireService.disableFire(id);
            return new ResponseEntity<>(updatedFireResponseDTO, HttpStatus.OK);

        } catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
