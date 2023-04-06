import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FuncionarioCreateComponent } from 'src/app/shared/components/funcionarios/funcionario-create/funcionario-create.component';
import { FuncionarioEditComponent } from 'src/app/shared/components/funcionarios/funcionario-edit/funcionario-edit.component';
import { FuncionariosListComponent } from 'src/app/shared/components/funcionarios/funcionarios-list/funcionarios-list.component';

import { OrderModule } from 'ngx-order-pipe';

const routes: Routes = [
  { path: '', component: FuncionariosListComponent },
  { path: 'create', component: FuncionarioCreateComponent },
  { path: 'edit/:id', component: FuncionarioEditComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule, OrderModule],
})
export class FuncionariosRoutingModule {}
