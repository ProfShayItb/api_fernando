package br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.domain.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    // Método personalizado para buscar um usuário pelo e-mail
    Optional<Usuario> findByEmail(String email);
}
