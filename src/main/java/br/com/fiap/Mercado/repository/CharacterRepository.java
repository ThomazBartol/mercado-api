package br.com.fiap.Mercado.repository;

import br.com.fiap.Mercado.model.MyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<MyCharacter, Long> {
}
