package com.springboot.apirestmysqldocker.repository;

import com.springboot.apirestmysqldocker.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByNomeContainsAndSobrenomeContains(String nome, String sobrenome);
}
