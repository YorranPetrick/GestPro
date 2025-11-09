package com.yo.GestPro.models.client;

public enum ClientAccount {
    ADMIN,
    USER;

    @Override
    public String toString() {
        return this.name();
    }
}
