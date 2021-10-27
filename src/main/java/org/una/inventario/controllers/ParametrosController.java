package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ParametrosDTO;
import org.una.inventario.services.IParametrosService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parametros")
@Api(tags = {"Parametros"})
public class ParametrosController {

    @Autowired
    private IParametrosService parametrosService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Parametros", response = ParametrosDTO.class, responseContainer = "List", tags = "Parametros")
    public @ResponseBody
    ResponseEntity<?> findAll(){
        try {
            Optional<List<ParametrosDTO>> result = parametrosService.findAll();
            if(result.isPresent()){
                return new ResponseEntity<>(result,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un parametro a partir de su id", response = ParametrosDTO.class, tags = "Parametros")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id){
        try{
            Optional<ParametrosDTO> parametrosFound = parametrosService.findById(id);
            if(parametrosFound.isPresent()){
                return new ResponseEntity<>(parametrosFound, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
            return  new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de parametros segun un aproximado a su nombre", response = ParametrosDTO.class, responseContainer = "List", tags="Parametros")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value="term") String term){
        try{
            Optional<List<ParametrosDTO>> result = parametrosService.findByNombreAproximateIgnoreCase(term);
            if(result.isPresent()){
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea un parametro", response = ParametrosDTO.class, tags="Parametros")
    public ResponseEntity<?> create(@RequestBody ParametrosDTO parametrosDTO){
        try {
            Optional<ParametrosDTO> parametrosCreated = parametrosService.create(parametrosDTO);
            return new ResponseEntity<>(parametrosCreated, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Actualiza un parametro a partir de su id", response = ParametrosDTO.class, tags="Parametros")
    public ResponseEntity<?> update(@PathVariable(value="id")Long id, @RequestBody ParametrosDTO parametrosModified){
        try{
            Optional<ParametrosDTO> parametrosUpdated = parametrosService.update(parametrosModified,id);
            if(parametrosUpdated.isPresent()){
                return new ResponseEntity<>(parametrosUpdated, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los parametros", response = ParametrosDTO.class, tags="Parametros")
    public ResponseEntity<?> deleteAll() throws Exception{
        try{
            parametrosService.deleteAll();
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un parametro a partir de su id", response = ParametrosDTO.class, tags="Parametros")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
        try{
            parametrosService.delete(id);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
