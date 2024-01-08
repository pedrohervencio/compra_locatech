package br.com.locacar.api.compra_locatech.dto;

public record VeiculoDTO(
        Long id,
        String marca,
        String modelo,
        Integer anoFabricacao,
        Integer anoModelo,
        String cor
) {
}
