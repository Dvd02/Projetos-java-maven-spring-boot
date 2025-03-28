package br.com.david.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.david.exceptions.ExceptionPessoaNaoEncontrada;
import br.com.david.model.Pessoa;
import br.com.david.repositories.PessoaRepository;

@Service
public class PessoaServices {

    @Autowired
    private PessoaRepository repositorio;

    public List<Pessoa> buscarTodas() {
        return repositorio.findAll();
    }

    public Pessoa buscarPorId(Long id) {
        return repositorio.findById(id)
            .orElseThrow(() -> new ExceptionPessoaNaoEncontrada(String.format("Pessoa nao encontrada com o id: %s", id)));
    }

    public Pessoa criar(Pessoa pessoa) {
        return repositorio.save(pessoa);
    }

    public Pessoa atualizar(Pessoa pessoa) {
        repositorio.findById(pessoa.getId())
            .orElseThrow(() -> new ExceptionPessoaNaoEncontrada(String.format("Pessoa nao encontrada com o id: %s", pessoa.getId())));

        return repositorio.save(pessoa);
    }

    public void deletar(Long id) {
        Pessoa entidade = repositorio.findById(id)
            .orElseThrow(() -> new ExceptionPessoaNaoEncontrada(String.format("Pessoa nao encontrada com o id: %s", id)));

        repositorio.delete(entidade);
    }
}
