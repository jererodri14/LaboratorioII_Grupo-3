package org.una.inventario.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.services.IDepartamentoService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
@Api(tags = {"Departamentos"})
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los departamentos", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<DepartamentoDTO>> result = departamentoService.findAll();
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una departamento a partir de su id", response = DepartamentoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<DepartamentoDTO> departamentoFound = departamentoService.findById(id);
            if (departamentoFound.isPresent()) {
                return new ResponseEntity<>(departamentoFound, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estados/{estado}")
    @ApiOperation(value = "Obtiene una lista de departamentos por su estado", response = DepartamentoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {

            Optional<List<DepartamentoDTO>> departamentoFound = departamentoService.findByEstado(estado);
            if (departamentoFound.isPresent()) {
                return new ResponseEntity<>(departamentoFound, HttpStatus.OK);
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
    @ApiOperation(value = "Crea un nuevo departamento", response = DepartamentoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> create(@RequestBody DepartamentoDTO departamentoDTO) {
        try {
            Optional<DepartamentoDTO> departamentoCreated = departamentoService.create(departamentoDTO);
            return new ResponseEntity<>(departamentoCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Actualiza un departamento a partir de su id", response = DepartamentoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody DepartamentoDTO departamentoModified) {
        try {
            Optional<DepartamentoDTO> departamentoUpdated = departamentoService.update(departamentoModified, id);
            if (departamentoUpdated.isPresent()) {
                return new ResponseEntity<>(departamentoUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un departamento a partir de su id", response = DepartamentoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            departamentoService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todos los departamentos", response = DepartamentoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            departamentoService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
