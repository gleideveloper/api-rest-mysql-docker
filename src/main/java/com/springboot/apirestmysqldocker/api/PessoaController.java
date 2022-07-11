package com.springboot.apirestmysqldocker.api;

import com.springboot.apirestmysqldocker.api.dto.PessoaDTO;
import com.springboot.apirestmysqldocker.model.Pessoa;
import com.springboot.apirestmysqldocker.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Profile("dev")
@AllArgsConstructor
public class PessoaController {

    PessoaService pessoaService;

    @GetMapping(value ="/todas")
    public List<PessoaDTO> findAll() {
        return pessoaService.findAll();
    }

    @GetMapping(value ="/{id}")
    public PessoaDTO findById(@PathVariable("id") Long id) {
        return pessoaService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<PessoaDTO> savePessoa(@RequestBody Pessoa pessoa){
        pessoaService.savePerson(pessoa);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable("id") long id, @RequestBody Pessoa pessoa) {
         pessoaService.updatePerson(id, pessoa);
    }
}