package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String token;
}
