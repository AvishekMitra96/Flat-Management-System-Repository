package com.fms.dto;

import lombok.Data;

@Data
public class FlatHolderRegistrationDto {
    private String flatNumber;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhone;
    private Long societyId;
}
