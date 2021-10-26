package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.TransaccionesDTO;
import org.una.inventario.entities.Transacciones;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.ITransaccionesRepository;
import org.una.inventario.utils.MapperUtils;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionesServiceImplementation implements ITransaccionesService{

    @Autowired
    private ITransaccionesRepository transaccionesRepository;


    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionesDTO> findById(Long id) {
        Optional<Transacciones> transaccion = transaccionesRepository.findById(id);
        if(transaccion.isEmpty()) throw new NotFoundInformationException();
        TransaccionesDTO transaccionesDTO = MapperUtils.DtoFromEntity(transaccion.get(),TransaccionesDTO.class);
        return Optional.ofNullable(transaccionesDTO);
    }

    private TransaccionesDTO getSavedTransaccionDTO(TransaccionesDTO transaccionesDTO){
        Transacciones transacciones = MapperUtils.EntityFromDto(transaccionesDTO, Transacciones.class);
        Transacciones transaccionesCreated = transaccionesRepository.save(transacciones);
        return MapperUtils.DtoFromEntity(transaccionesCreated, TransaccionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionesDTO>> findByUsuarioIdAndFechaCreacionBetween(Long usuarioId, Date startDate, Date endDate) {
        List<Transacciones> transaccionesList = transaccionesRepository.findByUsuarioIdAndFechaCreacionBetween(usuarioId, startDate, endDate);
        List<TransaccionesDTO> transaccionesDTOList = MapperUtils.DtoListFromEntityList(transaccionesList, TransaccionesDTO.class);
        return Optional.ofNullable(transaccionesDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionesDTO>> findByRolIdAndFechaCreacionBetween(Long rolId, Date startDate, Date endDate) {
        List<Transacciones> transaccionesList = transaccionesRepository.findByRolIdAndFechaCreacionBetween(rolId, startDate, endDate);
        List<TransaccionesDTO> transaccionesDTOList = MapperUtils.DtoListFromEntityList(transaccionesList, TransaccionesDTO.class);
        return Optional.ofNullable(transaccionesDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionesDTO>> findByObjetoAndFechaCreacionBetween(String objetoId, Date startDate, Date endDate) {
        List<Transacciones> transaccionesList = transaccionesRepository.findByObjetoAndFechaCreacionBetween(objetoId, startDate, endDate);
        List<TransaccionesDTO> transaccionesDTOList = MapperUtils.DtoListFromEntityList(transaccionesList, TransaccionesDTO.class);
        return Optional.ofNullable(transaccionesDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionesDTO>> findByFechaCreacionBetween(Date startDate, Date endDate) {
        List<Transacciones> transaccionesList = transaccionesRepository.findByFechaCreacionBetween(startDate, endDate);
        List<TransaccionesDTO> transaccionesDTOList = MapperUtils.DtoListFromEntityList(transaccionesList, TransaccionesDTO.class);
        return Optional.ofNullable(transaccionesDTOList);
    }

    @Override
    @Transactional
    public Optional<TransaccionesDTO> create(TransaccionesDTO transaccionesDTO) {
        return Optional.ofNullable(getSavedTransaccionDTO(transaccionesDTO));
    }

    @Override
    @Transactional
    public Optional<TransaccionesDTO> update(TransaccionesDTO transaccionesDTO, Long id) {
        if(transaccionesRepository.findById(id).isEmpty()) throw new NotFoundInformationException();
        return Optional.ofNullable(getSavedTransaccionDTO(transaccionesDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        transaccionesRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        transaccionesRepository.deleteAll();
    }
}
