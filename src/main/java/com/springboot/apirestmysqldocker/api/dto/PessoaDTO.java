package com.springboot.apirestmysqldocker.api.dto;

import com.springboot.apirestmysqldocker.model.Pessoa;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class PessoaDTO {
    private Long id;
    private String nome;
    private String sobrenome;

    /**
     * ModelMapper -> Transferi os atributos de Pessoa para PessoaDTO,
     * resumindo propriedades do objeto que será usado.
     */
    public static PessoaDTO converter(Pessoa pessoa){
        var modelMapper =  new ModelMapper();
        return modelMapper.map(pessoa,PessoaDTO.class);
    }
}
