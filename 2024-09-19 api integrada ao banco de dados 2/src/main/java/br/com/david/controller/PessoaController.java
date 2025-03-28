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
import br.com.david.mapper.curtom.PessoaDO;
import br.com.david.model.Pessoa;
import br.com.david.services.PessoaServices;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

  @Autowired
  private PessoaServices service;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<PessoaDO> buscarTodas() {
    return DozerMapper.parseListObjects(service.buscarTodas(), PessoaDO.class);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public PessoaDO buscarPorId(@PathVariable(value = "id") Long id) {
    return DozerMapper.parseObject(service.buscarPorId(id), PessoaDO.class);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Pessoa criar(@RequestBody Pessoa person) {
    return service.criar(person);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Pessoa atualizar(@RequestBody Pessoa person) {
    return service.atualizar(person);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> deletar(@PathVariable(value = "id") Long id) {
    service.deletar(id);
    return ResponseEntity.noContent().build();
  }

}
