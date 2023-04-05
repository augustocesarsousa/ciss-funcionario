import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IFuncionario } from 'src/app/shared/models/funcionario.model';
import { IPage } from 'src/app/shared/models/page.model';
import { environment } from 'src/environments/environment.development';

const baseUrl = environment.API_URL + '/funcionarios';

@Injectable({
  providedIn: 'root',
})
export class FuncionarioService {
  constructor(private http: HttpClient) {}

  public findByFilterPaged(page: number, size: number): Observable<any> {
    return this.http.get<IPage<IFuncionario>>(
      baseUrl + `?page=${page}&size=${size}`
    );
  }
}
