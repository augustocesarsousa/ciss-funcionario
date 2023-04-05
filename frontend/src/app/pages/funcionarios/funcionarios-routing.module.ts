import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FuncionarioCreateComponent } from 'src/app/shared/components/funcionarios/funcionario-create/funcionario-create.component';
import { FuncionariosListComponent } from 'src/app/shared/components/funcionarios/funcionarios-list/funcionarios-list.component';

const routes: Routes = [
  { path: '', component: FuncionariosListComponent },
  { path: 'create', component: FuncionarioCreateComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FuncionariosRoutingModule {}
