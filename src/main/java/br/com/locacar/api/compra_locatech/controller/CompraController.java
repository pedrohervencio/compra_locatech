package br.com.locacar.api.compra_locatech.controller;


import br.com.locacar.api.compra_locatech.dto.CompraDTO;
import br.com.locacar.api.compra_locatech.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/aquisicao/compra")
@RestController
public class CompraController {
    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }


    /*
    Lista compras
     */
    @GetMapping
    public ResponseEntity<Page<CompraDTO>> findAll(
            @PageableDefault(size = 10, page = 0, sort = "numero")Pageable pageable) {
        Page<CompraDTO> comprasDTO = compraService.findAll(pageable);
        return ResponseEntity.ok(comprasDTO);
    }

    /*
    Lista determinada compra
     */
    @GetMapping("/{numero}")
    public ResponseEntity<CompraDTO> findById(@PathVariable Long numero) {
        CompraDTO compraDTO = compraService.findById(numero);
        return ResponseEntity.ok(compraDTO);
    }

    /*
    Diretor solicita compra
     */
    @PostMapping
    public ResponseEntity<CompraDTO> save(@RequestBody CompraDTO compraDTO) {
        CompraDTO savedCompraDTO = compraService.save(compraDTO);
        return new ResponseEntity<>(savedCompraDTO, HttpStatus.CREATED);
    }

    /*
    Administrativo compra veiculo
     */
    @PutMapping("/compraveiculo/{numero}")
    public ResponseEntity<CompraDTO> compraVeiculo(
            @PathVariable Long numero,
            @RequestBody CompraDTO compraDTO) {
        CompraDTO updatedCompraDTO = compraService.compraVeiculo(numero, compraDTO);
        return ResponseEntity.ok(updatedCompraDTO);
    }

    /*
    Administrativo fatura veiculo
     */
    @PutMapping("/faturaveiculo/{numero}")
    public ResponseEntity<CompraDTO> faturaVeiculo(
            @PathVariable Long numero,
            @RequestBody CompraDTO compraDTO) {
        CompraDTO updatedCompraDTO = compraService.faturaVeiculo(numero, compraDTO);
        return ResponseEntity.ok(updatedCompraDTO);
    }

    /*
    Administrativo licencia veiculo
     */
    @PutMapping("/licenciaveiculo/{numero}")
    public ResponseEntity<CompraDTO> licenciaVeiculo(
            @PathVariable Long numero,
            @RequestBody CompraDTO compraDTO) {
        CompraDTO updatedCompraDTO = compraService.licenciaVeiculo(numero, compraDTO);
        return ResponseEntity.ok(updatedCompraDTO);
    }

    /*
    Administrativo segura veiculo
     */
    @PutMapping("/seguraveiculo/{numero}")
    public ResponseEntity<CompraDTO> seguraVeiculo(
            @PathVariable Long numero,
            @RequestBody CompraDTO compraDTO) {
        CompraDTO updatedCompraDTO = compraService.seguraVeiculo(numero, compraDTO);
        return ResponseEntity.ok(updatedCompraDTO);
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> delete(@PathVariable Long numero) {
        compraService.delete(numero);
        return ResponseEntity.noContent().build();
    }

}
