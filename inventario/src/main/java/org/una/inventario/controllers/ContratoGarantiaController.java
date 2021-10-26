package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ContratoGarantiaDTO;
import org.una.inventario.services.IContratoGarantiaService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contratosGarantias")
@Api(tags = {"ContratosGarantias"})
public class ContratoGarantiaController {

    @Autowired
    IContratoGarantiaService contratoGarantiaService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los contratos de garantias", response = ContratoGarantiaDTO.class, responseContainer = "List", tags = "ContratosGarantias")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ContratoGarantiaDTO>> result = contratoGarantiaService.findAll();
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
    @ApiOperation(value = "Obtiene un contrato de garantia a partir de su id", response = ContratoGarantiaDTO.class, tags = "ContratosGarantias")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ContratoGarantiaDTO> contratoGarantiaFound = contratoGarantiaService.findById(id);
            if (contratoGarantiaFound.isPresent()) {
                return new ResponseEntity<>(contratoGarantiaFound, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contratosGarantias/Activo/{id}")
    @ApiOperation(value = "Obtiene una lista de contratos de garantia a partir del id del activo", responseContainer = "List", response = ContratoGarantiaDTO.class, tags = "ContratosGarantias")
    public ResponseEntity<?> findByActivoId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<ContratoGarantiaDTO>> result = contratoGarantiaService.findByActivoId(id);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contratosGarantias/monto/{id}")
    @ApiOperation(value = "Obtiene una lista de contratos de garantia a partir del monto asegurado", responseContainer = "List", response = ContratoGarantiaDTO.class, tags = "ContratosGarantias")
    public ResponseEntity<?> findByMontoAsegurado(@PathVariable(value = "montoAsegurado") Double montoAsegurado) {
        try {
            Optional<List<ContratoGarantiaDTO>> result = contratoGarantiaService.findByMontoAsegurado(montoAsegurado);
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
    public ResponseEntity<?> create(@RequestBody ContratoGarantiaDTO contratoGarantiaDTO) {
        try {
            Optional<ContratoGarantiaDTO> contratoGarantiaCreated = contratoGarantiaService.create(contratoGarantiaDTO);
            return new ResponseEntity<>(contratoGarantiaCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ContratoGarantiaDTO contratoGarantiaModified) {
        try {
            Optional<ContratoGarantiaDTO> contratoGarantiaUpdated = contratoGarantiaService.update(contratoGarantiaModified, id);
            if (contratoGarantiaUpdated.isPresent()) {
                return new ResponseEntity<>(contratoGarantiaUpdated, HttpStatus.OK);

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
            contratoGarantiaService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            contratoGarantiaService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
