package br.com.nogueiranogueira.aularefatoracao.solidproject.service;

import br.com.nogueiranogueira.aularefatoracao.solidproject.dto.UsuarioDTO;
import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import br.com.nogueiranogueira.aularefatoracao.solidproject.repository.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GerenciadorUsuarioService {

    private final Map<String, RegraUsuario> regras;
    private final UsuarioCrudRepository usuarioRepository;
    private final NotificationService notificationService;

    @Autowired
    public GerenciadorUsuarioService(
            List<RegraUsuario> regraList,
            UsuarioCrudRepository usuarioRepository,
            NotificationService notificationService) {
        this.regras = regraList.stream()
                .collect(Collectors.toMap(RegraUsuario::getTipo, Function.identity()));
        this.usuarioRepository = usuarioRepository;
        this.notificationService = notificationService;
    }

    public Usuario criarUsuario(UsuarioDTO dto) {
        RegraUsuario regra = regras.get(dto.tipo());
        if (regra == null) {
            throw new IllegalArgumentException("Tipo inválido: " + dto.tipo());
        }
        Usuario usuario = regra.criar(dto);
        Usuario salvo = usuarioRepository.salvar(usuario);
        notificationService.enviarBoasVindas(salvo.getEmail(), salvo.getNome());
        return salvo;
    }
}
