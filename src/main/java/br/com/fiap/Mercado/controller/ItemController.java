package br.com.fiap.Mercado.controller;

import br.com.fiap.Mercado.model.Item;
import br.com.fiap.Mercado.model.MyCharacter;
import br.com.fiap.Mercado.repository.CharacterRepository;
import br.com.fiap.Mercado.repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
@Slf4j
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping
    @Operation(summary = "Listar items", description = "Retorna um array com todos os items")
    @Cacheable("items")
    public List<Item> index(){
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "items", allEntries = true)
    @Operation(summary = "Criar Item", description = "Cria um item no banco", responses = @ApiResponse(responseCode = "400", description = "Validação falhou"))
    @ResponseStatus(code = HttpStatus.CREATED)
    public Item create(@RequestBody @Valid Item item) {
        return repository.save(item);
    }
}
