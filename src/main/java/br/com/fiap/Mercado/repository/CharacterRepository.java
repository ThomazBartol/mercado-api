package br.com.fiap.Mercado.repository;

import br.com.fiap.Mercado.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
