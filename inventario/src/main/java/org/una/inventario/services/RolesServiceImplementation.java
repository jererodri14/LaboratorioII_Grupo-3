package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.RolDTO;
import org.una.inventario.entities.Rol;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IRolRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImplementation implements IRolServices{
    @Autowired
    private IRolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<RolDTO> findById(Long id) {
        Optional<Rol> rol = rolRepository.findById(id);
        if(rol.isEmpty()) throw new NotFoundInformationException();
        RolDTO rolDTO = MapperUtils.DtoFromEntity(rol.get(),RolDTO.class);
        return Optional.ofNullable(rolDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolDTO>> findAll() {
        List<RolDTO> rolDTOList = MapperUtils.DtoListFromEntityList(rolRepository.findAll(), RolDTO.class);
        return Optional.ofNullable(rolDTOList);
    }

    private RolDTO getSavedRolDTO (RolDTO rolDTO){
        Rol rol = MapperUtils.EntityFromDto(rolDTO, Rol.class);
        Rol rolCreated = rolRepository.save(rol);
        return MapperUtils.DtoFromEntity(rolCreated, RolDTO.class);
    }

    @Override
    @Transactional
    public Optional<RolDTO> create(RolDTO rolDTO) {
        return Optional.ofNullable(getSavedRolDTO(rolDTO));
    }

    @Override
    @Transactional
    public Optional<RolDTO> update(RolDTO rolDTO, Long id) {
        if(rolRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedRolDTO(rolDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        rolRepository.deleteAll();
    }
}
