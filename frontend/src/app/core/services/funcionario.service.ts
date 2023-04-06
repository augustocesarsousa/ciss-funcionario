import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IFilter } from 'src/app/shared/models/filter.model';
import { IFuncionarioCreate } from 'src/app/shared/models/funcionario-create.model';
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

    return this.http.get<IPage<IFuncionario>>(url);
  }

  public findById(idFuncionario: number): Observable<any> {
    return this.http.get(baseUrl + `/${idFuncionario}`);
  }

  public create(funcionario: IFuncionarioCreate): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    const options = { headers: headers };
    const body = JSON.stringify(funcionario);

    return this.http.post(baseUrl, body, options);
  }

  public update(funcionario: IFuncionario): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    const options = { headers: headers };
    const body = JSON.stringify(funcionario);

    return this.http.put(baseUrl + `/${funcionario.id}`, body, options);
  }
}
