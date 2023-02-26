package com.gmw.api.rest.tos;

public record UserDTO(Long id, String name, String email, byte[] avatar) {
}
