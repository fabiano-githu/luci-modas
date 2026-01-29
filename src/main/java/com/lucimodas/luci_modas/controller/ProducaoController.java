package com.lucimodas.luci_modas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lucimodas.luci_modas.model.Producao;
import com.lucimodas.luci_modas.service.ProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/producoes")
public class ProducaoController {

    @Autowired
    private ProducaoService service;

    /**
     * POST - cadastra produção com imagem
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Producao salvar(
            @RequestPart("producao") String producaoJson,
            @RequestPart("imagem") MultipartFile imagem
    ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Producao producao = mapper.readValue(producaoJson, Producao.class);

        String pasta = "C:/luci-modas/imagens/";
        Files.createDirectories(Paths.get(pasta));

        String nomeArquivo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
        Path caminhoImagem = Paths.get(pasta, nomeArquivo);
        Files.write(caminhoImagem, imagem.getBytes());

        producao.setCaminhoImagem(caminhoImagem.toString());

        return service.salvar(producao);
    }

    /**
     * GET - lista todas as produções
     */
    @GetMapping
    public List<Producao> listar() {
        return service.listar();
    }

    /**
     * PUT - atualiza produção (imagem opcional)
     */
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Producao atualizar(
            @PathVariable Long id,
            @RequestPart("producao") String producaoJson,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem
    ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Producao dadosAtualizados = mapper.readValue(producaoJson, Producao.class);

        Producao producaoExistente = service.buscarPorId(id);

        producaoExistente.setNomeProducao(dadosAtualizados.getNomeProducao());
        producaoExistente.setDonaTrabalho(dadosAtualizados.getDonaTrabalho());
        producaoExistente.setQuemTrouxe(dadosAtualizados.getQuemTrouxe());
        producaoExistente.setCodigo(dadosAtualizados.getCodigo());
        producaoExistente.setDescricao(dadosAtualizados.getDescricao());
        producaoExistente.setQuantidade(dadosAtualizados.getQuantidade());
        producaoExistente.setValorUnitario(dadosAtualizados.getValorUnitario());
        producaoExistente.setDataChegada(dadosAtualizados.getDataChegada());
        producaoExistente.setDataPrimeiraEntrega(dadosAtualizados.getDataPrimeiraEntrega());
        producaoExistente.setDataEntregaFinal(dadosAtualizados.getDataEntregaFinal());
        producaoExistente.setTamanho(dadosAtualizados.getTamanho());

        if (imagem != null && !imagem.isEmpty()) {

            String pasta = "C:/luci-modas/imagens/";
            Files.createDirectories(Paths.get(pasta));

            String nomeArquivo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
            Path caminhoImagem = Paths.get(pasta, nomeArquivo);
            Files.write(caminhoImagem, imagem.getBytes());

            producaoExistente.setCaminhoImagem(caminhoImagem.toString());
        }

        return service.salvar(producaoExistente);
    }

    /**
     * DELETE - remove produção
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
