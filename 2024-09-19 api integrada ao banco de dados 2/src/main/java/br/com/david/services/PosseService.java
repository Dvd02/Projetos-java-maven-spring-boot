package br.com.david.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.david.exceptions.ExceptionPosseNaoEncontrada;
import br.com.david.model.Posse;
import br.com.david.repositories.PosseRepository;

@Service
public class PosseService {
    
    @Autowired
    private PosseRepository posseRepository;

    public List<Posse> buscarTodas() {
        return posseRepository.findAll();
    }

    public Posse buscarPorId(Long id) {
        return posseRepository.findById(id)
            .orElseThrow(() -> new ExceptionPosseNaoEncontrada(String.format("Posse nao encontrada com o id: %s", id)));
    }
    
    public Posse criar(Posse posse) {
        return posseRepository.save(posse);
    }

    public List<Posse> buscarPorPessoaId(Long pessoaId) {
        return posseRepository.findByPessoaId(pessoaId);
    }

    public Posse atualizar(Posse posse) {
        posseRepository.findById(posse.getId())
            .orElseThrow(() -> new ExceptionPosseNaoEncontrada(String.format("Posse nao encontrada com o id: %s", posse.getId())));

        return posseRepository.save(posse);
    }

    public void deletar(Long id) {
        posseRepository.findById(id)
            .orElseThrow(() -> new ExceptionPosseNaoEncontrada(String.format("Posse nao encontrada com o id: %s", id)));

        posseRepository.deleteById(id);
    }
}
