package br.com.locacar.api.compra_locatech.dto;

import br.com.locacar.api.compra_locatech.entities.Administrativo;
import br.com.locacar.api.compra_locatech.entities.Diretor;
import br.com.locacar.api.compra_locatech.entities.Veiculo;
import br.com.locacar.api.compra_locatech.valueobjects.Proposta;
import br.com.locacar.api.compra_locatech.valueobjects.Status;

public record CompraDTO(
        Long numero,
        Diretor diretor,
        Administrativo administrativo,
        Veiculo veiculo,
        Proposta proposta,
        Status status
) {
}
