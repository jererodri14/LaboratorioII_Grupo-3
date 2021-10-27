package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.inventario.dto.AlertaDTO;
import org.una.inventario.dto.ProveedorDTO;
import org.una.inventario.entities.Proveedor;
import org.una.inventario.exceptions.NotFoundInformationException;
import org.una.inventario.repositories.IProveedorRepository;
import org.una.inventario.utils.MapperUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImplementation implements IProveedorService{
    @Autowired
    private IProveedorRepository proveedorRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ProveedorDTO>> findAll() {
        List<ProveedorDTO> proveedorDTOList = MapperUtils.DtoListFromEntityList(proveedorRepository.findAll(), ProveedorDTO.class);
        return Optional.ofNullable(proveedorDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProveedorDTO> findById(Long id) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
        if(proveedor.isEmpty()) throw new NotFoundInformationException();

        ProveedorDTO parametrosDTO = MapperUtils.DtoFromEntity(proveedor.get(),ProveedorDTO.class);
        return Optional.ofNullable(parametrosDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ProveedorDTO>> findByNombreAproximateIgnoreCase(String nombre) {
        List<Proveedor> proveedorList = proveedorRepository.findByNombreContainingIgnoreCase(nombre);
        List<ProveedorDTO> proveedorDTOList = MapperUtils.DtoListFromEntityList(proveedorList,ProveedorDTO.class);
        return Optional.ofNullable(proveedorDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProveedorDTO> findByCorreoElectronico(String correoElectronico) {
        ProveedorDTO proveedorDTO= MapperUtils.DtoFromEntity(proveedorRepository.findByCorreoElectronico(correoElectronico), ProveedorDTO.class);
        return Optional.ofNullable(proveedorDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ProveedorDTO>> findByEstado(String estado) {
        List<ProveedorDTO> proveedorDTOList= MapperUtils.DtoListFromEntityList(proveedorRepository.findByEstado(estado), ProveedorDTO.class);
        return Optional.ofNullable(proveedorDTOList);
    }

    private ProveedorDTO getSavedParametrosDTO(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = MapperUtils.EntityFromDto(proveedorDTO, Proveedor.class);
        Proveedor proveedorCreated = proveedorRepository.save(proveedor);
        return MapperUtils.DtoFromEntity(proveedorCreated, ProveedorDTO.class);
    }

    @Override
    @Transactional
    public Optional<ProveedorDTO> create(ProveedorDTO proveedorDTO) {
        return Optional.ofNullable(getSavedParametrosDTO(proveedorDTO));
    }

    @Override
    @Transactional
    public Optional<ProveedorDTO> update(ProveedorDTO proveedorDTO, Long id) {
        if (proveedorRepository.findById(id).isEmpty()) throw new NotFoundInformationException();

        return Optional.ofNullable(getSavedParametrosDTO(proveedorDTO));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        proveedorRepository.deleteAll();
    }
}
