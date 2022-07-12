package com.springboot.apirestmysqldocker.service;

import com.springboot.apirestmysqldocker.api.dto.PessoaDTO;
import com.springboot.apirestmysqldocker.exception.ObjectNotFoundException;
import com.springboot.apirestmysqldocker.model.Pessoa;
import com.springboot.apirestmysqldocker.repository.PessoaCustomRepository;
import com.springboot.apirestmysqldocker.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PessoaService {

    PessoaRepository pessoaRepository;
    PessoaCustomRepository pessoaCustomRepository;

    //Lambda: p -> PessoaDTO.converter(p) == PessoaDTO::converter
    public List<PessoaDTO> findAll() {
        var pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(PessoaDTO::converter)
                .collect(Collectors.toList());
    }

    public PessoaDTO findById(Long id) {
        var pessoa = pessoaRepository.findById(id);
        return pessoa.map(PessoaDTO::converter).orElseThrow(()-> new ObjectNotFoundException("Pessoa não encontrada!!!"));
    }

    public void savePerson(Pessoa pessoa) {
        Assert.isNull(pessoa.getId(),"Não foi possível savar o registro");
        PessoaDTO.converter(pessoaRepository.save(pessoa));
    }

    public PessoaDTO updatePerson(Long id, Pessoa pessoa) {
        Assert.notNull(id,"Não foi possível atualizar o registro");
        var p = pessoaRepository.findById(id);
        if(p.isPresent()){
            p.get().setNome(pessoa.getNome());
            p.get().setSobrenome(pessoa.getSobrenome());
            pessoaRepository.save(p.get());
            return PessoaDTO.converter(p.get());
        }
        throw new ObjectNotFoundException("Pessoa não encontrada!!!");
    }

    public void deletePerson(Long id) {
        pessoaRepository.deleteById(id);
    }

    public List<PessoaDTO> findByName(String nome, String sobrenome) {
        var pessoas = pessoaRepository.findByNomeContainsAndSobrenomeContains(nome,sobrenome);
        return pessoas.stream()
                .map(PessoaDTO::converter)
                .collect(Collectors.toList());
    }

    public List<PessoaDTO> findPerson(Long id, String nome, String sobrenome) {
        var pessoas = pessoaCustomRepository.findPerson(id, nome,sobrenome);
        return pessoas.stream()
                .map(PessoaDTO::converter)
                .collect(Collectors.toList());
    }
}
