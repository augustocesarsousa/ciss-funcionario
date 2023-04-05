import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';

import { LayoutComponent } from './shared/components/layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { FuncionarioCreateComponent } from './shared/components/funcionarios/funcionario-create/funcionario-create.component';

@NgModule({
  declarations: [AppComponent, LayoutComponent, HomeComponent, FuncionarioCreateComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
