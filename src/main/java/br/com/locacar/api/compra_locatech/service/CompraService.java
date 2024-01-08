package br.com.locacar.api.compra_locatech.service;

import br.com.locacar.api.compra_locatech.controller.exception.ControllerNotFoundException;
import br.com.locacar.api.compra_locatech.dto.CompraDTO;
import br.com.locacar.api.compra_locatech.dto.VeiculoDTO;
import br.com.locacar.api.compra_locatech.entities.Compra;
import br.com.locacar.api.compra_locatech.entities.Veiculo;
import br.com.locacar.api.compra_locatech.repository.CompraRepository;
import br.com.locacar.api.compra_locatech.valueobjects.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class CompraService {
    private final CompraRepository compraRepository;

    @Autowired
    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public Page<CompraDTO> findAll(Pageable pageable) {
        Page<Compra> compras = compraRepository.findAll(pageable);
        return compras.map(this::toDTO);
    }

    public CompraDTO findById(Long id) {
        Compra compra = compraRepository.findById(id).
                orElseThrow(() -> new ControllerNotFoundException("Compra não encontrada"));
        return toDTO(compra);
    }

    public CompraDTO save(CompraDTO compraDTO) {
        Compra compra = toEntity(compraDTO);
        compra.setStatus(Status.COMPRA_SOLICITADA);
        compra = compraRepository.save(compra);
        return toDTO(compra);
    }
    public CompraDTO compraVeiculo(Long numero, CompraDTO compraDTO) {

        return update(numero, compraDTO, Status.COMPRA_INICIADA);
    }

    public CompraDTO faturaVeiculo(Long numero, CompraDTO compraDTO) {
        compraDTO = update(numero, compraDTO, Status.VEICULO_FATURADO);
        elaboraPlano(compraDTO);
        return compraDTO;
    }

    public CompraDTO licenciaVeiculo(Long numero, CompraDTO compraDTO) {
        return update(numero, compraDTO, Status.VEICULO_LICENCIADO);
    }

    public CompraDTO seguraVeiculo(Long numero, CompraDTO compraDTO) {
        compraDTO = update(numero, compraDTO, Status.VEICULO_FATURADO);
        disponibilizaLocacao(compraDTO);
        return update(numero, compraDTO, Status.VEICULO_SEGURADO);
    }

    public CompraDTO update(Long numero, CompraDTO compraDTO, Status status) {
        try {
            Compra compra = compraRepository.getReferenceById(numero);
            compra.setDiretor(compraDTO.diretor());
            compra.setAdministrativo(compraDTO.administrativo());
            compra.setVeiculo(compraDTO.veiculo());
            compra.setProposta(compraDTO.proposta());
            compra.setStatus(status);
            compra = compraRepository.save(compra);
            return toDTO(compra);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Compra não encontrada.");
        }
    }

    public void delete(Long numero) {
        compraRepository.deleteById(numero);
        return;
    }

    private Compra toEntity(CompraDTO compraDTO) {
        return new Compra(
                compraDTO.numero(),
                compraDTO.diretor(),
                compraDTO.administrativo(),
                compraDTO.veiculo(),
                compraDTO.proposta(),
                compraDTO.status()
        );
    }

    private CompraDTO toDTO(Compra compra) {
        return new CompraDTO(
                compra.getNumero(),
                compra.getDiretor(),
                compra.getAdministrativo(),
                compra.getVeiculo(),
                compra.getProposta(),
                compra.getStatus()
        );
    }
    public void elaboraPlano(CompraDTO compraDTO) {

        Veiculo veiculo = compraDTO.veiculo();
        VeiculoDTO veiculoDTO = new VeiculoDTO(
                veiculo.getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getAnoFabricacao(),
                veiculo.getAnoModelo(),
                veiculo.getCor()
        );

        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = new URI("http://localhost:" + "3001" + "/veiculo");
            ResponseEntity<VeiculoDTO> result = restTemplate.postForEntity(uri, veiculoDTO, VeiculoDTO.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void disponibilizaLocacao(CompraDTO compraDTO) {

        Veiculo veiculo = compraDTO.veiculo();
        VeiculoDTO veiculoDTO = new VeiculoDTO(
                veiculo.getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getAnoFabricacao(),
                veiculo.getAnoModelo(),
                veiculo.getCor()
        );

        RestTemplate restTemplate = new RestTemplate();
        try {
            URI uri = new URI("http://localhost:" + "3002" + "/veiculo");
            ResponseEntity<VeiculoDTO> result = restTemplate.postForEntity(uri, veiculoDTO, VeiculoDTO.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
