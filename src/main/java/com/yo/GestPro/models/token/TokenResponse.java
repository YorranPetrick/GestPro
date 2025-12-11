package com.yo.GestPro.models.token;

import java.time.Instant;

public record TokenResponse(String tokenJwt, Instant expirationDate) {

}
