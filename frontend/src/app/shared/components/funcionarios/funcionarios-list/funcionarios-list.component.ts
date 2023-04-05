import { Component, EventEmitter, OnInit } from '@angular/core';
import { FuncionarioService } from 'src/app/core/services/funcionario.service';
import { IFuncionario } from 'src/app/shared/models/funcionario.model';
import { IPage } from 'src/app/shared/models/page.model';

@Component({
  selector: 'app-funcionarios-list',
  templateUrl: './funcionarios-list.component.html',
  styleUrls: ['./funcionarios-list.component.css'],
})
export class FuncionariosListComponent implements OnInit {
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

  constructor(private funcionarioService: FuncionarioService) {}

  ngOnInit(): void {
    this.getFuncionarios(0, 5);
  }

  public getFuncionarios(page: number, size: number): void {
    this.funcionarioService.findByFilterPaged(page, size).subscribe((res) => {
      this.page = res;
      console.log(this.page);
      console.log(this.page.first);
      console.log(this.page.last);
      console.log(page + ' ' + size);
    });
  }
}
