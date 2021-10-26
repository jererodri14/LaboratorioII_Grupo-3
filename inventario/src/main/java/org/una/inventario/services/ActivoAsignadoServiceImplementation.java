package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.entities.ActivoAsignado;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IActivoAsignadoRepository;
import org.una.inventario.utils.MapperUtils;
import java.util.List;
import java.util.Optional;

@Service
public class ActivoAsignadoServiceImplementation implements IActivoAsignadoService{

    @Autowired
    private IActivoAsignadoRepository activoAsignadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findAll() {
        List<ActivoAsignadoDTO> activoAsignadoDTOList = MapperUtils.DtoListFromEntityList(activoAsignadoRepository.findAll(), ActivoAsignadoDTO.class);
        return Optional.ofNullable(activoAsignadoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActivoAsignadoDTO> findById(Long id) {
        Optional<ActivoAsignado> activoAsignado = activoAsignadoRepository.findById(id);
        if (activoAsignado.isEmpty()) throw new NotFoundInformationException();

        ActivoAsignadoDTO activoAsignadoDTO = MapperUtils.DtoFromEntity(activoAsignado.get(), ActivoAsignadoDTO.class);
        return Optional.ofNullable(activoAsignadoDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findByActivoId(Long id) {
        List<ActivoAsignado> activoAsignadoList = activoAsignadoRepository.findByActivoId(id);
        List<ActivoAsignadoDTO> activoAsignadoDTOList = MapperUtils.DtoListFromEntityList(activoAsignadoList, ActivoAsignadoDTO.class);
        return Optional.ofNullable(activoAsignadoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoAsignadoDTO>> findByUsuarioId(Long id) {
        List<ActivoAsignado> activoAsignadoList = activoAsignadoRepository.findByUsuarioId(id);
        List<ActivoAsignadoDTO> activoAsignadoDTOList = MapperUtils.DtoListFromEntityList(activoAsignadoList, ActivoAsignadoDTO.class);
        return Optional.ofNullable(activoAsignadoDTOList);
    }

    @Override
    @Transactional
    public Optional<ActivoAsignadoDTO> create(ActivoAsignadoDTO activoAsignadoDTO) {
        return Optional.ofNullable(getSavedActivoAsignadoDTO(activoAsignadoDTO));
    }

    @Override
    @Transactional
    public Optional<ActivoAsignadoDTO> update(ActivoAsignadoDTO activoAsignadoDTO, Long id) {
        if (activoAsignadoRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedActivoAsignadoDTO(activoAsignadoDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        activoAsignadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        activoAsignadoRepository.deleteAll();
    }

    private ActivoAsignadoDTO getSavedActivoAsignadoDTO(ActivoAsignadoDTO activoAsignadoDTO) {
        ActivoAsignado activoAsignado = MapperUtils.EntityFromDto(activoAsignadoDTO, ActivoAsignado.class);
        ActivoAsignado activoAsignadoCreated = activoAsignadoRepository.save(activoAsignado);
        return MapperUtils.DtoFromEntity(activoAsignadoCreated, ActivoAsignadoDTO.class);
    }
}
