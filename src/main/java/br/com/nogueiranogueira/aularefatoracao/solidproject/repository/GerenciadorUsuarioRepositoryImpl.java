package br.com.nogueiranogueira.aularefatoracao.solidproject.repository;

import br.com.nogueiranogueira.aularefatoracao.solidproject.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GerenciadorUsuarioRepositoryImpl
        implements UsuarioCrudRepository, UsuarioFiltroRepository, UsuarioRelatorioRepository {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public void excluir(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<Usuario> buscarPorFiltroAvançados(String nome, String email, String tipoUsuario) {
        return usuarioRepository.findAll().stream()
                .filter(u -> nome == null || u.getNome().contains(nome))
                .filter(u -> email == null || u.getEmail().contains(email))
                .filter(u -> tipoUsuario == null || u.getTipo().equals(tipoUsuario))
                .toList();
    }

    @Override
    public long contarUsuariosPorTipo(String tipoUsuario) {
        return usuarioRepository.countByTipo(tipoUsuario);
    }

    @Override
    public List<Object[]> gerarRelatorioUsuariosPorTipo() {
        return usuarioRepository.findAll().stream()
                .collect(Collectors.groupingBy(Usuario::getTipo, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new Object[]{e.getKey(), e.getValue()})
                .toList();
    }
}
