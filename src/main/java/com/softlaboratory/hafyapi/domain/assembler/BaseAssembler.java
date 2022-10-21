package com.softlaboratory.hafyapi.domain.assembler;

import org.springframework.lang.NonNull;

import java.util.List;

public interface BaseAssembler<DAO, DTO> {

    @NonNull
    DTO convertToDto(@NonNull DAO dao);

    @NonNull
    List<DTO> convertToDtoList(@NonNull List<DAO> daoList);

    @NonNull
    DAO convertToDao(@NonNull DTO dto);

    @NonNull
    List<DAO> convertToDaoList(@NonNull List<DTO> dtoList);

}
