package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ParametrosDTO;
import org.una.inventario.entities.Parametros;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IParametrosRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;
@Service
public class ParametrosServiceImplementation implements IParametrosService {

    @Autowired
    private IParametrosRepository parametrosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosDTO>> findAll() {
        List<ParametrosDTO> parametrosDTOList = MapperUtils.DtoListFromEntityList(parametrosRepository.findAll(), ParametrosDTO.class);
        return Optional.ofNullable(parametrosDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametrosDTO> findById(Long id) {
        Optional<Parametros> parametros = parametrosRepository.findById(id);
        if(parametros.isEmpty()) throw new NotFoundInformationException();

        ParametrosDTO parametrosDTO = MapperUtils.DtoFromEntity(parametros.get(),ParametrosDTO.class);
        return Optional.ofNullable(parametrosDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosDTO>> findByNombreAproximateIgnoreCase(String nombre) {
        List<Parametros> parametrosList = parametrosRepository.findByNombreContainingIgnoreCase(nombre);
        List<ParametrosDTO> parametrosDTOList = MapperUtils.DtoListFromEntityList(parametrosList,ParametrosDTO.class);
        return Optional.ofNullable(parametrosDTOList);
    }

    private ParametrosDTO getSavedParametrosDTO(ParametrosDTO parametrosDTO) {
        Parametros parametros = MapperUtils.EntityFromDto(parametrosDTO, Parametros.class);
        Parametros parametrosCreated = parametrosRepository.save(parametros);
        return MapperUtils.DtoFromEntity(parametrosCreated, ParametrosDTO.class);
    }

    @Override
    @Transactional
    public Optional<ParametrosDTO> create(ParametrosDTO parametrosDTO) {
        return Optional.ofNullable(getSavedParametrosDTO(parametrosDTO));
    }

    @Override
    @Transactional
    public Optional<ParametrosDTO> update(ParametrosDTO parametrosDTO, Long id) {
        if (parametrosRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedParametrosDTO(parametrosDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        parametrosRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        parametrosRepository.deleteAll();
    }
}
