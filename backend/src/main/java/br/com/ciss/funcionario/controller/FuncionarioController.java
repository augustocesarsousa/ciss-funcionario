package br.com.ciss.funcionario.controller;

import java.net.URI;
import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.services.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioDTO = funcionarioService.create(funcionarioDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionarioDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(funcionarioDTO);
    }
}
