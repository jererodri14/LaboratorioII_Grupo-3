package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.entities.Activo;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IActivoRepository;
import org.una.inventario.utils.MapperUtils;
import java.util.List;
import java.util.Optional;

@Service
public class ActivoServiceImplementation implements IActivoService{

    @Autowired
    private IActivoRepository activoRepository;


    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findAll() {
        List<ActivoDTO> activoDTOList = MapperUtils.DtoListFromEntityList(activoRepository.findAll(), ActivoDTO.class);
        return Optional.ofNullable(activoDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActivoDTO> findById(Long id) {
        Optional<Activo> activo = activoRepository.findById(id);
        if (activo.isEmpty()) throw new NotFoundInformationException();

        ActivoDTO activoDTO = MapperUtils.DtoFromEntity(activo.get(), ActivoDTO.class);
        return Optional.ofNullable(activoDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByCategoriaId(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByProveedorId(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ActivoDTO>> findByMarcaId(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<ActivoDTO> create(ActivoDTO activoDTO) {
        return Optional.ofNullable(getSavedActivoDTO(activoDTO));
    }

    @Override
    @Transactional
    public Optional<ActivoDTO> update(ActivoDTO activoDTO, Long id) {
        if (activoRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedActivoDTO(activoDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        activoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        activoRepository.deleteAll();
    }

    private ActivoDTO getSavedActivoDTO(ActivoDTO activoDTO) {
        Activo activo = MapperUtils.EntityFromDto(activoDTO, Activo.class);
        Activo activoCreated = activoRepository.save(activo);
        return MapperUtils.DtoFromEntity(activoCreated, ActivoDTO.class);
    }
}
