package org.una.inventario.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.inventario.dto.DepartamentoDTO;
import org.una.inventario.dto.RolDTO;
import org.una.inventario.dto.UsuarioDTO;
import org.una.inventario.services.IDepartamentoService;
import org.una.inventario.services.IRolServices;
import org.una.inventario.services.IUsuarioService;

import java.util.Optional;

@Component
public class DataLoader implements ApplicationRunner {

    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolServices rolService;

    @Autowired
    private IDepartamentoService departamentoService;

    @Override
    public void run(ApplicationArguments args) {
        if (usuarioService.findByCedulaAproximate(cedula).get().size() == 0) {
            Optional<DepartamentoDTO> contabilidadDepartamento = departamentoService.create(DepartamentoDTO.builder().nombreDepartamento("Contabilidad").build());
            Optional<DepartamentoDTO> cajasDepartamento = departamentoService.create(DepartamentoDTO.builder().nombreDepartamento("Cajas").build());
            Optional<DepartamentoDTO> informaticaDepartamento = departamentoService.create(DepartamentoDTO.builder().nombreDepartamento("Informatica").build());

            Optional<RolDTO> colaboradorRol = rolService.create(RolDTO.builder().nombre(RolesTypes.ROLE_COLABORADOR.name()).build());
            Optional<RolDTO> auditorRol = rolService.create(RolDTO.builder().nombre(RolesTypes.ROLE_AUDITOR.name()).build());
            Optional<RolDTO> contadorRol = rolService.create(RolDTO.builder().nombre(RolesTypes.ROLE_CONTADOR.name()).build());
            Optional<RolDTO> usuarioRol = rolService.create(RolDTO.builder().nombre(RolesTypes.ROLE_USUARIO.name()).build());
            Optional<RolDTO> administradorRol = rolService.create(RolDTO.builder().nombre(RolesTypes.ROLE_ADMINISTRADOR.name()).build());

            UsuarioDTO cajeroUsuario = UsuarioDTO.builder()
                    .cedula("1472583690")
                    .nombreCompleto("Usuario Prueba Cajero")
                    .passwordEncriptado(password)
                    .departamento(cajasDepartamento.orElseThrow())
                    .rol(usuarioRol.orElseThrow()).build();
            usuarioService.create(cajeroUsuario);

            UsuarioDTO contadorUsuario = UsuarioDTO.builder()
                    .cedula("9876543210")
                    .nombreCompleto("Usuario Prueba Contador")
                    .esJefe(true)
                    .passwordEncriptado(password)
                    .departamento(contabilidadDepartamento.orElseThrow())
                    .rol(contadorRol.orElseThrow()).build();
            usuarioService.create(contadorUsuario);

            UsuarioDTO administradorUsuario = UsuarioDTO.builder()
                    .cedula(cedula)
                    .nombreCompleto("Usuario Administrador")
                    .passwordEncriptado(password)
                    .departamento(informaticaDepartamento.orElseThrow())
                    .rol(administradorRol.orElseThrow()).build();
            usuarioService.create(administradorUsuario);

            System.out.println("Se agrega el usuario inicial a la aplicación");
        }else {
            System.out.println("Se encontro el usuario administrador, continuando...");
        }
    }

}
