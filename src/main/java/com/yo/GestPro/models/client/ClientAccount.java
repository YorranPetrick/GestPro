package com.yo.GestPro.models.client;

public enum ClientAccount {
    ADMIN,
    USER;

    public String getRole() {
        return "ROLE_" + this.name();
    }

}
