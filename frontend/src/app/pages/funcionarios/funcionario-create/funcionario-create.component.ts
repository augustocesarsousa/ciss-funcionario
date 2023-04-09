import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FuncionarioService } from 'src/app/core/services/funcionario.service';
import { IFuncionarioCreate } from 'src/app/shared/models/funcionario-create.model';

@Component({
  selector: 'app-funcionario-create',
  templateUrl: './funcionario-create.component.html',
  styleUrls: ['./funcionario-create.component.css'],
})
export class FuncionarioCreateComponent implements OnInit {
  public form: FormGroup;
  public nomeFuncionario!: string;
  public sobrenomeFuncionario!: string;
  public emailFuncionario!: string;
  public nisFuncionario!: string;
  public link: string = 'funcionarios';

  public funcionario: IFuncionarioCreate = {
    nome: '',
    sobrenome: '',
    email: '',
    nis: '',
  };

  constructor(
    private formBuilder: FormBuilder,
    private funcionarioService: FuncionarioService,
    private toast: ToastrService,
    private route: Router
  ) {
    this.form = this.createForm();
  }

  ngOnInit(): void {}

  private createForm() {
    return this.formBuilder.group({
      nomeFuncionario: [
        null,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(30),
        ],
      ],
      sobrenomeFuncionario: [
        null,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(50),
        ],
      ],
      emailFuncionario: [null, [Validators.required, Validators.email]],
      nisFuncionario: [
        null,
        [Validators.required, Validators.pattern(/^[0-9]{11}\d*$/)],
      ],
    });
  }

  public isFormControlInvalid(controlName: string): boolean {
    return !!(
      this.form.get(controlName)?.invalid && this.form.get(controlName)?.touched
    );
  }

  public create(): void {
    this.funcionario.nome = this.nomeFuncionario;
    this.funcionario.sobrenome = this.sobrenomeFuncionario;
    this.funcionario.email = this.emailFuncionario;
    this.funcionario.nis = this.nisFuncionario;

    console.log(this.funcionario);

    this.funcionarioService.create(this.funcionario).subscribe(
      (res) => {
        this.route.navigate(['/funcionarios']);
        this.toast.success('Funcionário cadastrado com sucesso!');
      },
      (err) => {
        console.log(err);
        err.error.errors.map((e: any) => this.toast.error(e.message));
      }
    );
  }
}
