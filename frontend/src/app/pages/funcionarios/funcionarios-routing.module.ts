import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FuncionariosListComponent } from 'src/app/shared/components/funcionarios/funcionarios-list/funcionarios-list.component';

const routes: Routes = [{ path: '', component: FuncionariosListComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FuncionariosRoutingModule {}
