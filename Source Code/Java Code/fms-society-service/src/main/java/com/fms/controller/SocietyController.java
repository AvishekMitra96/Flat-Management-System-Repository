package com.fms.controller;

import com.fms.dto.FlatHolderRegistrationDto;
import com.fms.entity.Society;
import com.fms.service.SocietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/society")
@RequiredArgsConstructor
public class SocietyController {

    private final SocietyService societyService;

    @PostMapping("/register")
    public ResponseEntity<?> registerSociety(@RequestBody Society society) {
        return ResponseEntity.ok(societyService.registerSociety(society));
    }

    @PostMapping("/flat-holder/onboard")
    public ResponseEntity<?> onboardFlatHolder(@RequestBody FlatHolderRegistrationDto dto) {
        try {
            return ResponseEntity.ok(societyService.registerFlatHolder(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
