import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IFilter } from 'src/app/shared/models/filter.model';
import { IFuncionario } from 'src/app/shared/models/funcionario.model';
import { IPage } from 'src/app/shared/models/page.model';
import { environment } from 'src/environments/environment.development';

const baseUrl = environment.API_URL + '/funcionarios';

@Injectable({
  providedIn: 'root',
})
export class FuncionarioService {
  constructor(private http: HttpClient) {}

  public findByFilterPaged(filter: IFilter): Observable<any> {
    let url: string = baseUrl + `?page=${filter.page}&size=${filter.size}`;

    if (filter.id !== 0) url += `&id=${filter.id}`;
    if (filter.nome !== '') url += `&nome=${filter.nome.toLowerCase()}`;
    if (filter.sobrenome !== '')
      url += `&sobrenome=${filter.sobrenome.toLowerCase()}`;
    if (filter.email !== '') url += `&email=${filter.email.toLowerCase()}`;
    if (filter.nis !== '') url += `&nis=${filter.nis}`;

    console.log(url);
    return this.http.get<IPage<IFuncionario>>(url);
  }
}
