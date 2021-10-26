package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.services.IMarcaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marcas")
@Api(tags = {"Marcas"})
public class MarcaController {

    @Autowired
    private IMarcaService marcaService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las marcas", response = MarcaDTO.class, responseContainer = "List", tags = "Marcas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<MarcaDTO>> result = marcaService.findAll();
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
    @ApiOperation(value = "Obtiene una marca a partir de su id", response = MarcaDTO.class, tags = "Marcas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<MarcaDTO> marcaFound = marcaService.findById(id);
            if (marcaFound.isPresent()) {
                return new ResponseEntity<>(marcaFound, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/marcas/{nombre}")
    @ApiOperation(value = "Obtiene una lista de nombres de marcas", responseContainer = "List", response = MarcaDTO.class, tags = "Marcas")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            Optional<List<MarcaDTO>> result = marcaService.findByNombre(nombre);
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
    public ResponseEntity<?> create(@RequestBody MarcaDTO marcaDTO) {
        try {
            Optional<MarcaDTO> marcaCreated = marcaService.create(marcaDTO);
            return new ResponseEntity<>(marcaCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody MarcaDTO marcaModified) {
        try {
            Optional<MarcaDTO> marcaUpdated = marcaService.update(marcaModified, id);
            if (marcaUpdated.isPresent()) {
                return new ResponseEntity<>(marcaUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            marcaService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            marcaService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
