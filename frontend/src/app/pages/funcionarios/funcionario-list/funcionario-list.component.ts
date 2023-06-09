import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { FuncionarioService } from 'src/app/core/services/funcionario.service';
import { IFilter } from 'src/app/shared/models/filter.model';
import { IFuncionario } from 'src/app/shared/models/funcionario.model';
import { IPage } from 'src/app/shared/models/page.model';

@Component({
  selector: 'app-funcionario-list',
  templateUrl: './funcionario-list.component.html',
  styleUrls: ['./funcionario-list.component.css'],
})
export class FuncionariosListComponent implements OnInit {
  public form: FormGroup;
  public idFuncionario!: number;
  public nomeFuncionario!: string;
  public sobrenomeFuncionario!: string;
  public emailFuncionario!: string;
  public nisFuncionario!: string;
  public reverse: boolean = false;
  public key: string = 'id';
  public link: string = 'funcionarios';

  public filter: IFilter = {
    id: 0,
    nome: '',
    sobrenome: '',
    email: '',
    nis: '',
    page: 0,
    size: 5,
  };

  public page: IPage<IFuncionario> = {
    content: [],
    last: false,
    totalElements: 0,
    totalPages: 0,
    size: 0,
    number: 0,
    first: true,
    numberOfElements: 0,
    empty: true,
  };

  constructor(
    private formBuilder: FormBuilder,
    private funcionarioService: FuncionarioService,
    private toast: ToastrService
  ) {
    this.form = this.createForm();
  }

  ngOnInit(): void {
    this.getFuncionarios();
  }

  private createForm() {
    return this.formBuilder.group({
      idFuncionario: [null],
      nomeFuncionario: [null],
      sobrenomeFuncionario: [null],
      emailFuncionario: [null, Validators.email],
      nisFuncionario: [null, [Validators.pattern(/^[0-9]{11}\d*$/)]],
    });
  }

  public createFilter(page: number, size: number) {
    if (this.idFuncionario === null || this.idFuncionario === undefined) {
      this.filter.id = 0;
    } else {
      this.filter.id = this.idFuncionario;
    }

    if (this.nomeFuncionario === null || this.nomeFuncionario === undefined) {
      this.filter.nome = '';
    } else {
      this.filter.nome = this.nomeFuncionario;
    }

    if (
      this.sobrenomeFuncionario === null ||
      this.sobrenomeFuncionario === undefined
    ) {
      this.filter.sobrenome = '';
    } else {
      this.filter.sobrenome = this.sobrenomeFuncionario;
    }

    if (this.emailFuncionario === null || this.emailFuncionario === undefined) {
      this.filter.email = '';
    } else {
      this.filter.email = this.emailFuncionario;
    }

    if (this.nisFuncionario === null || this.nisFuncionario === undefined) {
      this.filter.nis = '';
    } else {
      this.filter.nis = this.nisFuncionario;
    }

    this.filter.page = page;
    this.filter.size = size;

    this.getFuncionarios();
  }

  public sort(key: string) {
    this.key = key;
    this.reverse = !this.reverse;
  }

  public getFuncionarios(): void {
    this.funcionarioService.findByFilterPaged(this.filter).subscribe((res) => {
      this.page = res;
      if (this.page.numberOfElements === 0) {
        this.toast.warning('Nenhum registro encontrado!');
      }
    });
  }

  public delete(idFuncionario: number, nomeFuncionario: string): void {
    this.funcionarioService.delete(idFuncionario).subscribe(
      (res) => {
        this.clear();
        this.toast.success(
          `Funcionário(a) ${nomeFuncionario} excluído com sucesso!`
        );
      },
      (err) => {
        err.error.errors.map((e: any) => this.toast.error(e.message));
      }
    );
  }

  public clear(): void {
    this.filter.id = 0;
    this.filter.nome = '';
    this.filter.sobrenome = '';
    this.filter.email = '';
    this.filter.nis = '';

    this.form.reset();

    this.getFuncionarios();
  }
}
