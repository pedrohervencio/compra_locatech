package br.com.locacar.api.compra_locatech.valueobjects;

import jakarta.persistence.Embeddable;

public enum Status {
    COMPRA_SOLICITADA, COMPRA_INICIADA,
    VEICULO_FATURADO, VEICULO_LICENCIADO, VEICULO_SEGURADO
}
