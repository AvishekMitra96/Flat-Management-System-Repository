package com.fms.service;

import com.fms.dto.FlatHolderRegistrationDto;
import com.fms.dto.UserRegistrationRequest;
import com.fms.entity.Flat;
import com.fms.entity.Society;
import com.fms.repository.FlatRepository;
import com.fms.repository.SocietyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SocietyService {

    private final SocietyRepository societyRepository;
    private final FlatRepository flatRepository;
    private final RestTemplate restTemplate;

    public Society registerSociety(Society society) {
        return societyRepository.save(society);
    }

    @Transactional
    public Flat registerFlatHolder(FlatHolderRegistrationDto dto) {
        if (flatRepository.findByFlatNumber(dto.getFlatNumber()).isPresent()) {
            throw new RuntimeException("Flat already registered!");
        }
        
        Society society = societyRepository.findById(dto.getSocietyId())
            .orElseThrow(() -> new RuntimeException("Society not found"));

        // 1. Create user in auth service
        String tempPassword = UUID.randomUUID().toString().substring(0, 8); // Temporary password
        
        UserRegistrationRequest userReq = new UserRegistrationRequest();
        userReq.setUsername(dto.getOwnerEmail()); // Using email as username
        userReq.setPassword(tempPassword);
        userReq.setRole("FLAT_HOLDER");
        userReq.setFirstLogin(true);

        ResponseEntity<String> authResponse = restTemplate.postForEntity(
                "http://fms-auth-service/api/auth/internal/register", userReq, String.class);
        
        if (!authResponse.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to create user in Auth service");
        }

        // 2. Save flat details
        Flat flat = new Flat();
        flat.setFlatNumber(dto.getFlatNumber());
        flat.setOwnerName(dto.getOwnerName());
        flat.setOwnerEmail(dto.getOwnerEmail());
        flat.setOwnerPhone(dto.getOwnerPhone());
        flat.setSociety(society);

        System.out.println("Created User: " + dto.getOwnerEmail() + " Password: " + tempPassword);

        return flatRepository.save(flat);
    }
}
