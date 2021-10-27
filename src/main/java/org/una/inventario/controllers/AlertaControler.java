package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.dto.AlertaDTO;
import org.una.inventario.services.IActivoAsignadoService;
import org.una.inventario.services.IAlertaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alertas")
@Api(tags = {"Alertas"})
public class AlertaControler {

    @Autowired
    private IAlertaService alertaService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las alertas", response = AlertaDTO.class, responseContainer = "List", tags = "Alerta")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<AlertaDTO>> result = alertaService.findAll();
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
    @ApiOperation(value = "Obtiene una alerta asignada a partir de su id", response = AlertaDTO.class, tags = "Alerta")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<AlertaDTO> alertaFound = alertaService.findById(id);
            if (alertaFound.isPresent()) {
                return new ResponseEntity<>(alertaFound, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tipo/{tipo}")
    @ApiOperation(value = "Obtiene una lista de alertas asignadas a partir del tipo de la alerta", responseContainer = "List", response = AlertaDTO.class, tags = "Alerta")
    public ResponseEntity<?> findByTipo(@PathVariable(value = "tipo") String tipo) {
        try {
            Optional<List<AlertaDTO>> result = alertaService.findByTipo(tipo);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/responsable/{responsable}")
    @ApiOperation(value = "Obtiene una lista de alertas asignadas a partir del responsable de la alerta", responseContainer = "List", response = AlertaDTO.class, tags = "Alerta")
    public ResponseEntity<?> findByResponsable(@PathVariable(value = "responsable") Long responsable) {
        try {
            Optional<List<AlertaDTO>> result = alertaService.findByResponsable(responsable);
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
    @ApiOperation(value = "Obtiene una lista de alertas asignadas a partir del estado de la alerta", responseContainer = "List", response = AlertaDTO.class, tags = "Alerta")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") String estado) {
        try {
            Optional<List<AlertaDTO>> result = alertaService.findByEstado(estado);
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
    public ResponseEntity<?> create(@RequestBody AlertaDTO alertaDTO) {
        try {
            Optional<AlertaDTO> alertaCreated = alertaService.create(alertaDTO);
            return new ResponseEntity<>(alertaCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AlertaDTO alertaModified) {
        try {
            Optional<AlertaDTO> alertaUpdated = alertaService.update(alertaModified, id);
            if (alertaUpdated.isPresent()) {
                return new ResponseEntity<>(alertaUpdated, HttpStatus.OK);

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
            alertaService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            alertaService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
