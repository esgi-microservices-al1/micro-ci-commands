package com.esgi.microservices.dto.factory;

import java.text.ParseException;

public interface Converter<E,DTO> {
    E convertToEntity(DTO dto) throws ParseException;
    DTO convertToDto(E e);
}
