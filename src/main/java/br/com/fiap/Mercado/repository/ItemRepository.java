package br.com.fiap.Mercado.repository;

import br.com.fiap.Mercado.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
