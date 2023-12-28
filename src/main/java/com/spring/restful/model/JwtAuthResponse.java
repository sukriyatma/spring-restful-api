package com.spring.restful.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtAuthResponse {

    private String token;

    private String refreshToken;

}
