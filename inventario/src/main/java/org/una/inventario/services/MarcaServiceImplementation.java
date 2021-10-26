package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.entities.Marca;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IMarcaRepository;
import org.una.inventario.utils.MapperUtils;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImplementation implements IMarcaService{

    @Autowired
    private IMarcaRepository marcaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<MarcaDTO>> findAll() {
        List<MarcaDTO> marcaDTOList = MapperUtils.DtoListFromEntityList(marcaRepository.findAll(), MarcaDTO.class);
        return Optional.ofNullable(marcaDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MarcaDTO> findById(Long id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isEmpty()) throw new NotFoundInformationException();

        MarcaDTO marcaDTO = MapperUtils.DtoFromEntity(marca.get(), MarcaDTO.class);
        return Optional.ofNullable(marcaDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<MarcaDTO>> findByNombre(String nombre) {
        List<Marca> marcas = marcaRepository.findByNombre(nombre);
        List<MarcaDTO> marcaDTOList = MapperUtils.DtoListFromEntityList(marcas, MarcaDTO.class);
        return Optional.ofNullable(marcaDTOList);
    }

    @Override
    @Transactional
    public Optional<MarcaDTO> create(MarcaDTO marcaDTO) {
        return Optional.ofNullable(getSavedMarcaDTO(marcaDTO));
    }

    @Override
    @Transactional
    public Optional<MarcaDTO> update(MarcaDTO marcaDTO, Long id) {
        if (marcaRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedMarcaDTO(marcaDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        marcaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        marcaRepository.deleteAll();
    }

    private MarcaDTO getSavedMarcaDTO(MarcaDTO marcaDTO) {
        Marca marca = MapperUtils.EntityFromDto(marcaDTO, Marca.class);
        Marca marcaCreated = marcaRepository.save(marca);
        return MapperUtils.DtoFromEntity(marcaCreated, MarcaDTO.class);
    }
}
