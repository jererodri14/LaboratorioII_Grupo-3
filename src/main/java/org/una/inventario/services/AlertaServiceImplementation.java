package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.dto.AlertaDTO;
import org.una.inventario.dto.CategoriaDTO;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Alerta;
import org.una.inventario.entities.Categoria;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IAlertaRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;
@Service
public class AlertaServiceImplementation implements IAlertaService{

    @Autowired
    IAlertaRepository alertaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AlertaDTO>> findAll() {
        List<AlertaDTO> alertaDTOList = MapperUtils.DtoListFromEntityList(alertaRepository.findAll(), AlertaDTO.class);
        return Optional.ofNullable(alertaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlertaDTO> findById(Long id) {
        Optional<Alerta> alerta = alertaRepository.findById(id);
        if (alerta.isEmpty()) throw new NotFoundInformationException();

        AlertaDTO alertaDTO = MapperUtils.DtoFromEntity(alerta.get(), AlertaDTO.class);
        return Optional.ofNullable(alertaDTO);
    }

    @Override
    public Optional<List<AlertaDTO>> findByTipo(String tipo) {
        List<AlertaDTO> alertaDTOList= MapperUtils.DtoListFromEntityList(alertaRepository.findByTipo(tipo), AlertaDTO.class);
        return Optional.ofNullable(alertaDTOList);
    }

    @Override
    public Optional<List<AlertaDTO>> findByEstado(String estado) {
        List<AlertaDTO> alertaDTOList= MapperUtils.DtoListFromEntityList(alertaRepository.findByEstado(estado), AlertaDTO.class);
        return Optional.ofNullable(alertaDTOList);
    }

    @Override
    public Optional<List<AlertaDTO>> findByResponsable(Long id) {
        List<AlertaDTO> alertaDTOList= MapperUtils.DtoListFromEntityList(alertaRepository.findByResponsable(id), AlertaDTO.class);
        return Optional.ofNullable(alertaDTOList);
    }

    @Override
    @Transactional
    public Optional<AlertaDTO> create(AlertaDTO alertaDTO) {
        return Optional.ofNullable(getSavedAlertaDTO(alertaDTO));
    }

    @Override
    @Transactional
    public Optional<AlertaDTO> update(AlertaDTO alertaDTO, Long id) {
        if (alertaRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedAlertaDTO(alertaDTO));
    }

    @Override
    public void delete(Long id) {
        alertaRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        alertaRepository.deleteAll();
    }

    private AlertaDTO getSavedAlertaDTO(AlertaDTO alertaDTO) {
        Alerta alerta = MapperUtils.EntityFromDto(alertaDTO, Alerta.class);
        Alerta alertaCreated = alertaRepository.save(alerta);
        return MapperUtils.DtoFromEntity(alertaCreated, AlertaDTO.class);
    }
}
