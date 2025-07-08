package org.training.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.training.reactive.dto.SirenRequestDTO;
import org.training.reactive.dto.SirenResponseDTO;
import org.training.reactive.model.Siren;
import org.training.reactive.security.security_entity.UserPrincipal;
import org.training.reactive.service.SirenService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SirenController {
    @Autowired
    SirenService sirenService;


    @PostMapping("/sirens")
    public ResponseEntity<SirenResponseDTO> addSiren(@RequestBody SirenRequestDTO sirenDTO) {
        return new ResponseEntity<>(sirenService.addSiren(sirenDTO), HttpStatus.CREATED);
    }

    @GetMapping("/sirens")
    public ResponseEntity<List<SirenResponseDTO>> findAll(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<SirenResponseDTO> sirens = sirenService.findAll();
        return ResponseEntity.ok(sirens);
    }

    @PostMapping("/sirens/{id}")
    public ResponseEntity<SirenResponseDTO> updateSiren(@PathVariable long id, @RequestBody SirenResponseDTO sirenResponseDTO) {
        try {
            SirenResponseDTO updatedSirenResponseDTO = sirenService.updateSiren(sirenResponseDTO);
            return new ResponseEntity<>(updatedSirenResponseDTO, HttpStatus.OK);

        } catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/sirens/{id}")
    public ResponseEntity deleteSiren(@PathVariable long id) {
        try {
            sirenService.deleteSiren(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }




}
