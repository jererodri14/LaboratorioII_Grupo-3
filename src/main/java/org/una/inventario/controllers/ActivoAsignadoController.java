package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.ActivoAsignadoDTO;
import org.una.inventario.services.IActivoAsignadoService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activosAsignados")
@Api(tags = {"ActivosAsignados"})
public class ActivoAsignadoController {

    @Autowired
    private IActivoAsignadoService activoAsignadoService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los activos asignados", response = ActivoAsignadoDTO.class, responseContainer = "List", tags = "ActivosAsignados")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('AUDITOR')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ActivoAsignadoDTO>> result = activoAsignadoService.findAll();
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
    @ApiOperation(value = "Obtiene una activo asignado a partir de su id", response = ActivoAsignadoDTO.class, tags = "ActivosAsignados")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('AUDITOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ActivoAsignadoDTO> activoAsignadoFound = activoAsignadoService.findById(id);
            if (activoAsignadoFound.isPresent()) {
                return new ResponseEntity<>(activoAsignadoFound, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activosAsignados/Usuario/{id}")
    @ApiOperation(value = "Obtiene una lista de activos asignados a partir del id del usuario", responseContainer = "List", response = ActivoAsignadoDTO.class, tags = "ActivosAsignados")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('AUDITOR')")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<ActivoAsignadoDTO>> result = activoAsignadoService.findByUsuarioId(id);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activosAsignados/Activo/{id}")
    @ApiOperation(value = "Obtiene una lista de activos asignados a partir del id del activo", responseContainer = "List", response = ActivoAsignadoDTO.class, tags = "ActivosAsignados")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('AUDITOR')")
    public ResponseEntity<?> findByActivoId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<ActivoAsignadoDTO>> result = activoAsignadoService.findByActivoId(id);
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
    @ApiOperation(value = "Crea un activo asignado", responseContainer = "List", response = ActivoAsignadoDTO.class, tags = "ActivosAsignados")
    @ResponseBody
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('AUDITOR')")
    public ResponseEntity<?> create(@RequestBody ActivoAsignadoDTO activoAsignadoDTO) {
        try {
            Optional<ActivoAsignadoDTO> activoAsignadoCreated = activoAsignadoService.create(activoAsignadoDTO);
            return new ResponseEntity<>(activoAsignadoCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza una activos asignados a partir del id", responseContainer = "List", response = ActivoAsignadoDTO.class, tags = "ActivosAsignados")
    @ResponseBody
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('AUDITOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ActivoAsignadoDTO activoAsignadoModified) {
        try {
            Optional<ActivoAsignadoDTO> activoAsignadoUpdated = activoAsignadoService.update(activoAsignadoModified, id);
            if (activoAsignadoUpdated.isPresent()) {
                return new ResponseEntity<>(activoAsignadoUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un activo asignado a partir del id", responseContainer = "List", response = ActivoAsignadoDTO.class, tags = "ActivosAsignados")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('AUDITOR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            activoAsignadoService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina los activos asignados", responseContainer = "List", response = ActivoAsignadoDTO.class, tags = "ActivosAsignados")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('AUDITOR')")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            activoAsignadoService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
