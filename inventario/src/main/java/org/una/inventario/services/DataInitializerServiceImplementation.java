package org.una.inventario.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Rol;
import org.una.inventario.entities.Usuario;
import org.una.inventario.repositories.IDepartamentoRepository;
import org.una.inventario.repositories.IRolRepository;
import org.una.inventario.repositories.IUsuarioRepository;

@Service
public class DataInitializerServiceImplementation {
    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    /*@Override
    public void initDevelopData() {

        Departamento patentesDepartamento = departamentoRepository.save(Departamento.builder().nombre("Licencias y patentes").build());
        Departamento cajasDepartamento = departamentoRepository.save(Departamento.builder().nombre("Cajas").build());

        Rol colaboradorRol = rolRepository.save(Rol.builder().nombre("Colaborador").build());
        Rol usuarioRol = rolRepository.save(Rol.builder().nombre("Usuario").build());
        Rol auditorRol = rolRepository.save(Rol.builder().nombre("Auditor").build());
        Rol contadorRol = rolRepository.save(Rol.builder().nombre("Contador").build());
        Rol administradorRol = rolRepository.save(Rol.builder().nombre("Administrador").build());


        Usuario cajeroUsuario = new Usuario();
        cajeroUsuario.setCedula("0123456789");
        cajeroUsuario.setNombreCompleto("Usuario Prueba cajero");
        cajeroUsuario.setPasswordEncriptado("Una2021");
        cajeroUsuario.setDepartamento(cajasDepartamento);
        cajeroUsuario.setRol(usuarioRol);
        usuarioRepository.save(cajeroUsuario);

        Usuario coordinadorDepartamentoUsuario = new Usuario();
        coordinadorDepartamentoUsuario.setCedula("9876543210");
        coordinadorDepartamentoUsuario.setNombreCompleto("Usuario Prueba Coordinador");
        coordinadorDepartamentoUsuario.setEsJefe(true);
        coordinadorDepartamentoUsuario.setPasswordEncriptado("Una2021");
        coordinadorDepartamentoUsuario.setDepartamento(patentesDepartamento);
        coordinadorDepartamentoUsuario.setRol(administradorRol);
        usuarioRepository.save(coordinadorDepartamentoUsuario);
    }*/

    /*@Override
    public void deleteAllData() {
        rolRepository.deleteAll();
        usuarioRepository.deleteAll();
        departamentoRepository.deleteAll();
    }*/
}
