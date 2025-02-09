package com.khubaib.lmbk.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDTO {

    private UUID id;

    @NotBlank
    @NotNull
    private String customerName;
    private String customerEmail;
    @Version
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
