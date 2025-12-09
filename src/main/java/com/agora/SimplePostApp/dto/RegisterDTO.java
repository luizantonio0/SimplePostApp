package com.agora.SimplePostApp.dto;

import com.agora.SimplePostApp.enums.UserRoles;

public record RegisterDTO(
        String name,
        String email,
        String password,
        UserRoles role
) {
}
