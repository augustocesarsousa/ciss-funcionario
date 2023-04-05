import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-funcionario-create',
  templateUrl: './funcionario-create.component.html',
  styleUrls: ['./funcionario-create.component.css'],
})
export class FuncionarioCreateComponent {
  public form: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.form = this.createForm();
  }

  private createForm() {
    return this.formBuilder.group({
      nomeFuncionario: [null, [Validators.required]],
      sobrenomeFuncionario: [null],
      emailFuncionario: [null, Validators.email],
      nisFuncionario: [null, [Validators.pattern(/^[0-9]{11}\d*$/)]],
    });
  }
}
