package com.softlaboratory.hafyapi.domain.assembler;

import com.softlaboratory.hafyapi.domain.dao.RolesDao;
import com.softlaboratory.hafyapi.domain.dto.RolesDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleAssembler implements BaseAssembler<RolesDao, RolesDto> {

    @Autowired
    ModelMapper mapper;

    @Override
    public RolesDto convertToDto(RolesDao rolesDao) {
        return mapper.map(rolesDao, RolesDto.class);
    }

    @Override
    public List<RolesDto> convertToDtoList(List<RolesDao> daoList) {
        List<RolesDto> dtoList = new ArrayList<>();
        for (RolesDao dao : daoList) {
            dtoList.add(mapper.map(dao, RolesDto.class));
        }
        return dtoList;
    }

    @Override
    public RolesDao convertToDao(RolesDto rolesDto) {
        return mapper.map(rolesDto, RolesDao.class);
    }

    @Override
    public List<RolesDao> convertToDaoList(List<RolesDto> dtoList) {
        List<RolesDao> rolesDaoList = new ArrayList<>();
        for (RolesDto dto : dtoList) {
            rolesDaoList.add(mapper.map(dto, RolesDao.class));
        }
        return rolesDaoList;
    }
}
