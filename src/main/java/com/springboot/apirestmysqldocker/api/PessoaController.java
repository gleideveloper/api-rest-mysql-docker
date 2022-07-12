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

    @GetMapping(value = "/todas")
    public List<PessoaDTO> findAll() {
        return pessoaService.findAll();
    }

    @GetMapping(value = "/{id}")
    public PessoaDTO findById(@PathVariable("id") Long id) {
        return pessoaService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<PessoaDTO> savePessoa(@RequestBody Pessoa pessoa) {
        pessoaService.savePerson(pessoa);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> updatePerson(@PathVariable("id") long id, @RequestBody Pessoa pessoa) {
        var p = pessoaService.updatePerson(id, pessoa);
        return p != null ?
                ResponseEntity.ok(p) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") Long id) {
        pessoaService.deletePerson(id);
    }

    @GetMapping("/filter")
    public List<PessoaDTO> findByName(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "sobrenome", required = false) String sobrenome) {
        return pessoaService.findByName(nome, sobrenome);
    }

    @GetMapping("/custom")
    public List<PessoaDTO> findByName(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "sobrenome", required = false) String sobrenome) {
        return pessoaService.findPerson(id, nome, sobrenome);
    }

}
