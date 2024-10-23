package br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.domain.Usuario;
import br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    // Obter usuário por ID
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Obter todos os usuários com detalhes
    public List<Map<String, Object>> obterUsuariosComDetalhes() {
        String sql = "SELECT u.id AS idUsuario, " +
                     "u.nome AS nomeUsuario, " +
                     "u.email AS emailUsuario, " +
                     "u.nivelAcesso AS nivelAcessoUsuario, " +
                     "u.foto AS fotoUsuario, " +
                     "u.statusUsuario AS statusUsuario " +
                     "FROM Usuario u";
        return jdbcTemplate.queryForList(sql);
    }

    // Incluir novo usuário
    public Usuario incluir(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Atualizar usuário
    public Usuario atualizar(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        return null; // ou lançar uma exceção
    }

    // Excluir usuário
    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Obter usuário por e-mail
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
