package com.example.carros.domain.dto;

import com.example.carros.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserDTO {
    private String login;
    private String nome;
    private String email;

    // token jwt
    private String token;

    public static com.example.carros.domain.dto.UserDTO create(User user, String token) {
        ModelMapper modelMapper = new ModelMapper();
        com.example.carros.domain.dto.UserDTO dto = modelMapper.map(user, com.example.carros.domain.dto.UserDTO.class);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}
