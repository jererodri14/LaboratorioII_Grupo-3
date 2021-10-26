package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ValuacionDTO;
import org.una.inventario.services.IValuacionService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/valuaciones")
@Api(tags = {"Valuaciones"})
public class ValuacionController {

    @Autowired
    private IValuacionService valuacionService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las valuaciones", response = ValuacionDTO.class, responseContainer = "List", tags = "Valuaciones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ValuacionDTO>> result = valuacionService.findAll();
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/valuaciones/Inventario/{id}")
    @ApiOperation(value = "Obtiene una lista de valuaciones a partir del id del inventario", responseContainer = "List", response = ValuacionDTO.class, tags = "Valuaciones")
    public ResponseEntity<?> findByInventarioId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<ValuacionDTO>> result = valuacionService.findByInventarioId(id);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/valuaciones/Activo/{id}")
    @ApiOperation(value = "Obtiene una lista de valuaciones a partir del id del activo", responseContainer = "List", response = ValuacionDTO.class, tags = "Valuaciones")
    public ResponseEntity<?> findByActivoId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<ValuacionDTO>> result = valuacionService.findByActivoId(id);
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
    public ResponseEntity<?> create(@RequestBody ValuacionDTO valuacionDTO) {
        try {
            Optional<ValuacionDTO> valuacionCreated = valuacionService.create(valuacionDTO);
            return new ResponseEntity<>(valuacionCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ValuacionDTO valuacionModified) {
        try {
            Optional<ValuacionDTO> valuacionUpdated = valuacionService.update(valuacionModified, id);
            if (valuacionUpdated.isPresent()) {
                return new ResponseEntity<>(valuacionUpdated, HttpStatus.OK);

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
            valuacionService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            valuacionService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
