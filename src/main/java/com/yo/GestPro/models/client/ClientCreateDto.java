package com.yo.GestPro.models.client;

public record ClientCreateDto(
        String loginClient,
        String passwordClient,
        ClientAccount clientAccount
) {
}
