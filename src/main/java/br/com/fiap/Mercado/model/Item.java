package br.com.fiap.Mercado.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "não pode estar em branco")
    private String name;
    @NotNull(message = "não pode estar vazio")
    private ItemType type;
    @NotNull(message = "não pode estar vazio")
    private ItemRarity rarity;
    @Positive(message = "não pode ser negativo ou 0")
    private Integer price;
    @ManyToOne
    private Character owner;
}
