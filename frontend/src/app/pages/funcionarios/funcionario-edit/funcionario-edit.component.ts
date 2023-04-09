import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FuncionarioService } from 'src/app/core/services/funcionario.service';
import { IFuncionario } from 'src/app/shared/models/funcionario.model';

@Component({
  selector: 'app-funcionario-edit',
  templateUrl: './funcionario-edit.component.html',
  styleUrls: ['./funcionario-edit.component.css'],
})
export class FuncionarioEditComponent implements OnInit {
  public form: FormGroup;
  public idFuncionario!: number;
  public nomeFuncionario!: string;
  public sobrenomeFuncionario!: string;
  public emailFuncionario!: string;
  public nisFuncionario!: string;
  public link: string = 'funcionarios';

  public funcionario: IFuncionario = {
    id: 0,
    nome: '',
    sobrenome: '',
    email: '',
    nis: '',
  };

  constructor(
    private formBuilder: FormBuilder,
    private activedRoute: ActivatedRoute,
    private funcionarioService: FuncionarioService,
    private toast: ToastrService,
    private route: Router
  ) {
    this.form = this.createForm();
  }

  ngOnInit(): void {
    this.idFuncionario = Number(this.activedRoute.snapshot.paramMap.get('id'));
    this.findById(this.idFuncionario);
  }

  private createForm() {
    return this.formBuilder.group({
      idFuncionario: [null],
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

  isFormControlInvalid(controlName: string): boolean {
    return !!(
      this.form.get(controlName)?.invalid && this.form.get(controlName)?.touched
    );
  }

  private findById(idFuncionario: number): void {
    this.funcionarioService.findById(idFuncionario).subscribe(
      (res) => {
        this.idFuncionario = res.id;
        this.nomeFuncionario = res.nome;
        this.sobrenomeFuncionario = res.sobrenome;
        this.emailFuncionario = res.email;
        this.nisFuncionario = res.nis;
      },
      (err) => {
        this.toast.error(err.error.message);
      }
    );
  }

  public update(): void {
    this.funcionario.id = this.idFuncionario;
    this.funcionario.nome = this.nomeFuncionario;
    this.funcionario.sobrenome = this.sobrenomeFuncionario;
    this.funcionario.email = this.emailFuncionario;
    this.funcionario.nis = this.nisFuncionario;

    this.funcionarioService.update(this.funcionario).subscribe(
      (res) => {
        this.route.navigate(['/funcionarios']);
        this.toast.success('Funcionário editado com sucesso!');
      },
      (err) => {
        err.error.errors.map((e: any) => this.toast.error(e.message));
      }
    );
  }
}
