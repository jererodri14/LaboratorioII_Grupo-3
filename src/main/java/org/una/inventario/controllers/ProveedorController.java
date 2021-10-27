package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.AlertaDTO;
import org.una.inventario.dto.ParametrosDTO;
import org.una.inventario.dto.ProveedorDTO;
import org.una.inventario.entities.Proveedor;
import org.una.inventario.services.IParametrosService;
import org.una.inventario.services.IProveedorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
@Api(tags = {"Proveedores"})
public class ProveedorController {
    @Autowired
    private IProveedorService proveedorService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Proveedores", response = ProveedorDTO.class, responseContainer = "List", tags = "Proveedores")
    public @ResponseBody
    ResponseEntity<?> findAll(){
        try {
            Optional<List<ProveedorDTO>> result = proveedorService.findAll();
            if(result.isPresent()){
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un proveedor a partir de su id", response = ProveedorDTO.class, tags = "Proveedores")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){
        try{
            Optional<ProveedorDTO> proveedorFound = proveedorService.findById(id);
            if(proveedorFound.isPresent()){
                return new ResponseEntity<>(proveedorFound, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{nombre}")
    @ApiOperation(value = "Obtiene una lista de proveedores segun un aproximado a su nombre", response = ProveedorDTO.class, responseContainer = "List", tags="Proveedores")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value="nombre") String nombre){
        try{
            Optional<List<ProveedorDTO>> result = proveedorService.findByNombreAproximateIgnoreCase(nombre);
            if(result.isPresent()){
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/correo/{correoElectronico}")
    @ApiOperation(value = "Obtiene una lista de proveedores asignados a partir del correo electronico del proveedor", responseContainer = "List", response = ProveedorDTO.class, tags = "Proveedores")
    public ResponseEntity<?> findByCorreoElectronico(@PathVariable(value = "correoElectronico") String correoElectronico) {
        try {
            Optional<ProveedorDTO> result = proveedorService.findByCorreoElectronico(correoElectronico);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{estado}")
    @ApiOperation(value = "Obtiene una lista de alertas asignadas a partir del estado del proveedor", responseContainer = "List", response = ProveedorDTO.class, tags = "Proveedores")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<ProveedorDTO>> result = proveedorService.findByEstado(estado);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea un proveedor", response = ProveedorDTO.class, tags="Proveedores")
    public ResponseEntity<?> create(@RequestBody ProveedorDTO proveedorDTO){
        try {
            Optional<ProveedorDTO> proveedorCreated = proveedorService.create(proveedorDTO);
            return new ResponseEntity<>(proveedorCreated, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Actualiza un proveedor a partir de su id", response = ProveedorDTO.class, tags="Proveedores")
    public ResponseEntity<?> update(@PathVariable(value="id")Long id, @RequestBody ProveedorDTO proveedorModified){
        try{
            Optional<ProveedorDTO> proveedorUpdated = proveedorService.update(proveedorModified,id);
            if(proveedorUpdated.isPresent()){
                return new ResponseEntity<>(proveedorUpdated, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los proveedores", response = ProveedorDTO.class, tags="Proveedores")
    public ResponseEntity<?> deleteAll() throws Exception{
        try{
            proveedorService.deleteAll();
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un proveedor a partir de su id", response = ProveedorDTO.class, tags="Proveedores")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
        try{
            proveedorService.delete(id);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
