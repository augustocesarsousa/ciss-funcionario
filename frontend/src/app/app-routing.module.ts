import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { OrderModule } from 'ngx-order-pipe';
import { FuncionariosListComponent } from './pages/funcionarios/funcionario-list/funcionario-list.component';
import { FuncionarioCreateComponent } from './pages/funcionarios/funcionario-create/funcionario-create.component';
import { FuncionarioEditComponent } from './pages/funcionarios/funcionario-edit/funcionario-edit.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'funcionarios', component: FuncionariosListComponent },
  { path: 'funcionarios/create', component: FuncionarioCreateComponent },
  { path: 'funcionarios/edit/:id', component: FuncionarioEditComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule, OrderModule],
})
export class AppRoutingModule {}
