package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class RegraUsuarioAdmin implements RegraUsuario {

    @Override
    public String getTipo() {
        return "ADMIN";
    }

    @Override
    public Usuario criar(UsuarioDTO dto) {
        validarEmail(dto.email());
        Usuario usuario = new Usuario(dto.nome(), dto.email(), dto.tipo());
        usuario.setPontos(500);
        return usuario;
    }

    private void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }
}
