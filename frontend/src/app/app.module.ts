import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { ROUTES } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { InventarioComponent } from './telas/inventario/inventario.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MainService } from './services/main.service';
import { CadastroComponent } from './telas/cadastro/cadastro.component';
import { HistoricoComponent } from './telas/historico/historico.component';
import { DetalheComponent } from './telas/detalhe/detalhe.component';

@NgModule({
  declarations: [
    AppComponent,
    InventarioComponent,
    CadastroComponent,
    HistoricoComponent,
    DetalheComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(ROUTES),
    BrowserAnimationsModule,
    MatIconModule
  ],
  providers: [MainService],
  bootstrap: [AppComponent],
  exports:[RouterModule]
})
export class AppModule { }
