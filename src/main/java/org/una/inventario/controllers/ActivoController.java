package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ActivoDTO;
import org.una.inventario.services.IActivoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activos")
@Api(tags = {"Activos"})
public class ActivoController {

    @Autowired
    private IActivoService activoService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Activos", response = ActivoDTO.class, responseContainer = "List", tags = "Activos")
    public @ResponseBody
    ResponseEntity<?> findAll(){
        try {
            Optional<List<ActivoDTO>> result = activoService.findAll();
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
    @ApiOperation(value = "Obtiene un activo a partir de su id", response = ActivoDTO.class, tags = "Activos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){
        try{
            Optional<ActivoDTO> activoFound = activoService.findById(id);
            if(activoFound.isPresent()){
                return new ResponseEntity<>(activoFound, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/{id}")
    @ApiOperation(value = "Obtiene una lista de activos a partir del id de su categoria", response = ActivoDTO.class, responseContainer = "List", tags = "Activos")
    public ResponseEntity<?> findByCategoriaId(@PathVariable(value = "id") Long id){
        try{
            Optional<List<ActivoDTO>> activoFound = activoService.findByCategoriaId(id);
            if(activoFound.isPresent()){
                return new ResponseEntity<>(activoFound, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/proveedor/{id}")
    @ApiOperation(value = "Obtiene una lista de activos a partir del id de su proveedor", response = ActivoDTO.class, responseContainer = "List", tags = "Activos")
    public ResponseEntity<?> findByProveedorId(@PathVariable(value = "id") Long id){
        try{
            Optional<List<ActivoDTO>> activoFound = activoService.findByProveedorId(id);
            if(activoFound.isPresent()){
                return new ResponseEntity<>(activoFound, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/marca/{id}")
    @ApiOperation(value = "Obtiene una lista de activos a partir del id de su marca", response = ActivoDTO.class, responseContainer = "List", tags = "Activos")
    public ResponseEntity<?> findByMarcaId(@PathVariable(value = "id") Long id){
        try{
            Optional<List<ActivoDTO>> activoFound = activoService.findByMarcaId(id);
            if(activoFound.isPresent()){
                return new ResponseEntity<>(activoFound, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea un activo", response = ActivoDTO.class, tags="Activos")
    public ResponseEntity<?> create(@RequestBody ActivoDTO activoDTO){
        try {
            Optional<ActivoDTO> activoCreated = activoService.create(activoDTO);
            return new ResponseEntity<>(activoCreated, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Actualiza un activo a partir de su id", response = ActivoDTO.class, tags="Activos")
    public ResponseEntity<?> update(@PathVariable(value="id")Long id, @RequestBody ActivoDTO activosModified){
        try{
            Optional<ActivoDTO> activoUpdated = activoService.update(activosModified,id);
            if(activoUpdated.isPresent()){
                return new ResponseEntity<>(activoUpdated, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los activos", response = ActivoDTO.class, tags="Activos")
    public ResponseEntity<?> deleteAll() throws Exception{
        try{
            activoService.deleteAll();
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un activo a partir de su id", response = ActivoDTO.class, tags="Activos")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
        try{
            activoService.delete(id);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
