import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FuncionariosRoutingModule } from './funcionarios-routing.module';
import { FuncionariosListComponent } from 'src/app/shared/components/funcionarios/funcionarios-list/funcionarios-list.component';

@NgModule({
  declarations: [FuncionariosListComponent],
  imports: [CommonModule, FuncionariosRoutingModule],
})
export class FuncionariosModule {}
