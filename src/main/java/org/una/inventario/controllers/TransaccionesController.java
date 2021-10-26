package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.TransaccionesDTO;
import org.una.inventario.services.ITransaccionesService;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionesController {

    @Autowired
    private ITransaccionesService transaccionesService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una transaccion a partir de su id", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<TransaccionesDTO> transaccionFound = transaccionesService.findById(id);
            if (transaccionFound.isPresent()) {
                return new ResponseEntity<>(transaccionFound, HttpStatus.OK);
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
    @ApiOperation(value = "Crea una nueva transaccion", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> create(@RequestBody TransaccionesDTO transaccionesDTO) {
        try {
            Optional<TransaccionesDTO> transacionCreated = transaccionesService.create(transaccionesDTO);
            return new ResponseEntity<>(transacionCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Actualiza una transaccion a partir de su id", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TransaccionesDTO transaccionModified) {
        try {
            Optional<TransaccionesDTO> transaccionUpdated = transaccionesService.update(transaccionModified, id);
            if (transaccionUpdated.isPresent()) {
                return new ResponseEntity<>(transaccionUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 @GetMapping("/usuarioId/{term}")
    @ApiOperation(value = "Obtiene una transaccion a partir de su usuario y un rango de fechas", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findByUsuarioIdAndFechaCreacionBetween(@PathVariable(value = "term") Long term, Date startDate, Date endDate) {
        try {
            Optional<List<TransaccionesDTO>> result = transaccionesService.findByUsuarioIdAndFechaCreacionBetween(term,startDate,endDate);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rolId/{term}")
    @ApiOperation(value = "Obtiene una transaccion a partir del rol de usuario y un rango de fechas", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findByRolIdAndFechaCreacionBetween(@PathVariable(value = "term") Long term, Date startDate, Date endDate) {
        try {
            Optional<List<TransaccionesDTO>> result = transaccionesService.findByRolIdAndFechaCreacionBetween(term,startDate,endDate);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/objetoId/{term}")
    @ApiOperation(value = "Obtiene una transaccion a partir de un objeto y un rango de fechas", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findByObjetoAndFechaCreacionBetween(@PathVariable(value = "term") String term, @PathVariable(value = "startDate") Date startDate, @PathVariable(value = "endDate")Date endDate) {
        try {
            Optional<List<TransaccionesDTO>> result = transaccionesService.findByObjetoAndFechaCreacionBetween(term,startDate,endDate);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("transaccion/{term}")
    @ApiOperation(value = "Obtiene una transaccion a partir de un rango de fechas", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findByFechaCreacionBetween(@PathVariable(value = "term") Date startDate, Date endDate) {
        try {
            Optional<List<TransaccionesDTO>> result = transaccionesService.findByFechaCreacionBetween(startDate,endDate);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        try {
            transaccionesService.delete(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() throws Exception {
        try {
            transaccionesService.deleteAll();
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
