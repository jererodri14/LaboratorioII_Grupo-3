package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ValuacionDTO;
import org.una.inventario.entities.Valuacion;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IValuacionRepository;
import org.una.inventario.utils.MapperUtils;
import java.util.List;
import java.util.Optional;

@Service
public class ValuacionServiceImplementation implements IValuacionService{

    @Autowired
    private IValuacionRepository valuacionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionDTO>> findAll() {
        List<ValuacionDTO> valuacionDTOList = MapperUtils.DtoListFromEntityList(valuacionRepository.findAll(), ValuacionDTO.class);
        return Optional.ofNullable(valuacionDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ValuacionDTO> findById(Long id) {
        Optional<Valuacion> valuacion = valuacionRepository.findById(id);
        if (valuacion.isEmpty()) throw new NotFoundInformationException();

        ValuacionDTO valuacionDTO = MapperUtils.DtoFromEntity(valuacion.get(), ValuacionDTO.class);
        return Optional.ofNullable(valuacionDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionDTO>> findByInventarioId(Long id) {
        List<Valuacion> valuacion = valuacionRepository.findByInventarioId(id);
        List<ValuacionDTO> valuacionDTOList = MapperUtils.DtoListFromEntityList(valuacion, ValuacionDTO.class);
        return Optional.ofNullable(valuacionDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ValuacionDTO>> findByActivoId(Long id) {
        List<Valuacion> valuacion = valuacionRepository.findByActivoId(id);
        List<ValuacionDTO> valuacionDTOList = MapperUtils.DtoListFromEntityList(valuacion, ValuacionDTO.class);
        return Optional.ofNullable(valuacionDTOList);
    }

    @Override
    @Transactional
    public Optional<ValuacionDTO> create(ValuacionDTO valuacionDTO) {
        return Optional.ofNullable(getSavedValuacionDTO(valuacionDTO));
    }

    @Override
    @Transactional
    public Optional<ValuacionDTO> update(ValuacionDTO usuarioDTO, Long id) {
        if (valuacionRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedValuacionDTO(usuarioDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        valuacionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        valuacionRepository.deleteAll();
    }

    private ValuacionDTO getSavedValuacionDTO(ValuacionDTO valuacionDTO) {
        Valuacion valuacion = MapperUtils.EntityFromDto(valuacionDTO, Valuacion.class);
        Valuacion valuacionCreated = valuacionRepository.save(valuacion);
        return MapperUtils.DtoFromEntity(valuacionCreated, ValuacionDTO.class);
    }
}
