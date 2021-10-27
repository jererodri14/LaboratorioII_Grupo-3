package org.una.inventario.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.una.inventario.dto.CategoriaDTO;
import org.una.inventario.services.ICategoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@Api(tags = {"Categorias"})
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping()
    @ApiOperation(value="Obtiene una lista de todas las categorias", response = CategoriaDTO.class, responseContainer = "List", tags = "Categorias")
    public @ResponseBody
    ResponseEntity<?> findAll(){
        try{
            Optional<List<CategoriaDTO>> result = categoriaService.findAll();
            if(result.isPresent()){
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Obtiene una categoria apartir de su id", response = CategoriaDTO.class,  tags = "Categorias")
    public @ResponseBody
    ResponseEntity<?> findById(@PathVariable(value="id")Long id){
        try{
            Optional<CategoriaDTO> result = categoriaService.findById(id);
            if(result.isPresent()){
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de categorias segun un aproximado a su nombre", response = CategoriaDTO.class, responseContainer = "List", tags="Categorias")
    public ResponseEntity<?> findByNombreAproximateIgnoreCase(@PathVariable(value="term") String term){
        try{
            Optional<List<CategoriaDTO>> result = categoriaService.findByNombreAproximateIgnoreCase(term);
            if(result.isPresent()){
                return new ResponseEntity<>(result, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de categorias segun su estado", response = CategoriaDTO.class, responseContainer = "List", tags="Categorias")
    public ResponseEntity<?> findByEstado(@PathVariable(value="term") String term){
        try{
            Optional<List<CategoriaDTO>> result = categoriaService.findByEstado(term);
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
    @ApiOperation(value = "Crea una categoria", response = CategoriaDTO.class, tags="Categorias")
    public ResponseEntity<?> create(@RequestBody CategoriaDTO categoriaDTO){
        try {
            Optional<CategoriaDTO> categoriaCreated = categoriaService.create(categoriaDTO);
            return new ResponseEntity<>(categoriaCreated, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Actualiza una categoria a partir de su id", response = CategoriaDTO.class, tags="Categorias")
    public ResponseEntity<?> update(@PathVariable(value="id")Long id, @RequestBody CategoriaDTO categoriaModified){
        try{
            Optional<CategoriaDTO> categoriaUpdated = categoriaService.update(categoriaModified,id);
            if(categoriaUpdated.isPresent()){
                return new ResponseEntity<>(categoriaUpdated, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina todas las categorias", response = CategoriaDTO.class, tags="Categorias")
    public ResponseEntity<?> deleteAll() throws Exception{
        try{
            categoriaService.deleteAll();
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina una categoria a partir de su id", response = CategoriaDTO.class, tags="Categorias")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception{
        try{
            categoriaService.delete(id);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
