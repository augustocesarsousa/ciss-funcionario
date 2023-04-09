import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './pages/home/home.component';
import { FuncionarioCreateComponent } from './pages/funcionarios/funcionario-create/funcionario-create.component';
import { ToastrModule } from 'ngx-toastr';
import { FuncionarioEditComponent } from './pages/funcionarios/funcionario-edit/funcionario-edit.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';
import { FooterComponent } from './shared/components/footer/footer.component';
import { FuncionariosListComponent } from './pages/funcionarios/funcionario-list/funcionario-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FuncionariosListComponent,
    FuncionarioCreateComponent,
    FuncionarioEditComponent,
    NavbarComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
