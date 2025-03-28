package br.com.david.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.david.mapper.DozerMapper;
import br.com.david.mapper.curtom.PosseDO;
import br.com.david.model.Posse;
import br.com.david.services.PosseService;

@RestController
@RequestMapping("/posses")
public class PosseController {

    @Autowired
    private PosseService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PosseDO> listarTodas() {
        return DozerMapper.parseListObjects(service.buscarTodas(), PosseDO.class);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PosseDO buscarPorId(@PathVariable Long id) {
        return DozerMapper.parseObject(service.buscarPorId(id), PosseDO.class);
    }

    @GetMapping(value = "/pessoa/{pessoaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PosseDO> buscarPorPessoaId(@PathVariable Long pessoaId) {
        return DozerMapper.parseListObjects(service.buscarPorPessoaId(pessoaId), PosseDO.class);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Posse criar(@RequestBody Posse posse) {
        return service.criar(posse);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Posse atualizar(@PathVariable Long id, @RequestBody Posse posse) {
        return service.atualizar(posse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
