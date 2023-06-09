package br.com.ciss.funcionario.controller;

import java.net.URI;

import br.com.ciss.funcionario.dtos.FuncionarioCreateDTO;
import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.dtos.FuncionarioUpdateDTO;
import br.com.ciss.funcionario.services.FuncionarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioCreateDTO> create(@Valid @RequestBody FuncionarioCreateDTO funcionarioDTO) {
        funcionarioDTO = funcionarioService.create(funcionarioDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionarioDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(funcionarioDTO);
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioDTO>> findByFilterPaged(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "nome", defaultValue = "", required = false) String nome,
            @RequestParam(value = "sobrenome", defaultValue = "", required = false) String sobrenome,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "nis", required = false) String nis,
            @PageableDefault(page = 0, size = 5) Pageable pageable
    ) {
        Page<FuncionarioDTO> funcionarios = funcionarioService.findByFilterPaged(id, nome.trim(), sobrenome.trim(), email, nis, pageable);

        return ResponseEntity.ok().body(funcionarios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        FuncionarioDTO funcionarioDTO = funcionarioService.findById(id);

        return ResponseEntity.ok().body(funcionarioDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioUpdateDTO> update(@PathVariable Long id, @Valid @RequestBody FuncionarioUpdateDTO funcionarioUpdateDTO) {
        funcionarioUpdateDTO = funcionarioService.update(id, funcionarioUpdateDTO);

        return ResponseEntity.ok().body(funcionarioUpdateDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> delete(@PathVariable Long id) {
        funcionarioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
