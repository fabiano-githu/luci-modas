package com.lucimodas.luci_modas.service;

import com.lucimodas.luci_modas.model.Producao;
import com.lucimodas.luci_modas.repository.ProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProducaoService {

    @Autowired
    private ProducaoRepository repository;

    /**
     * SALVAR ou ATUALIZAR produção
     * Regra de negócio: calcula valor total automaticamente
     */
    public Producao salvar(Producao producao) {

        if (producao.getValorUnitario() != null && producao.getQuantidade() != null) {
            BigDecimal valorTotal = producao.getValorUnitario()
                    .multiply(BigDecimal.valueOf(producao.getQuantidade()));

            producao.setValorTotal(valorTotal);
        }

        return repository.save(producao);
    }

    /**
     * LISTAR todas as produções
     */
    public List<Producao> listar() {
        return repository.findAll();
    }

    /**
     * BUSCAR produção por ID
     */
    public Producao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Produção não encontrada com ID: " + id)
                );
    }

    /**
     * DELETAR produção por ID
     */
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produção não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}
