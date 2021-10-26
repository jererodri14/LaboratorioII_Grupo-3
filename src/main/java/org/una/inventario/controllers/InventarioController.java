package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.InventarioDTO;
import org.una.inventario.services.IInventarioService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventarios")
@Api(tags = {"Inventarios"})
public class InventarioController {

    @Autowired
    private IInventarioService inventarioService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Inventarios", response = InventarioDTO.class, responseContainer = "List", tags = "Inventarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<InventarioDTO>> result = inventarioService.findAll();
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
    @ApiOperation(value = "Obtiene una inventario a partir de su id", response = InventarioDTO.class, tags = "Inventarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<InventarioDTO> inventarioFound = inventarioService.findById(id);
            if (inventarioFound.isPresent()) {
                return new ResponseEntity<>(inventarioFound, HttpStatus.OK);
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
    @ApiOperation(value = "Crea un nuevo inventario", response = InventarioDTO.class, tags = "Inventarios")
    public ResponseEntity<?> create(@RequestBody InventarioDTO inventarioDTO) {
        try {
            Optional<InventarioDTO> inventarioCreated = inventarioService.create(inventarioDTO);
            return new ResponseEntity<>(inventarioCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Actualiza un inventario a partir de su id", response = InventarioDTO.class, tags = "Inventarios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody InventarioDTO inventarioModified) {
        try {
            Optional<InventarioDTO> inventarioUpdated = inventarioService.update(inventarioModified, id);
            if (inventarioUpdated.isPresent()) {
                return new ResponseEntity<>(inventarioUpdated, HttpStatus.OK);

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
            inventarioService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            inventarioService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
