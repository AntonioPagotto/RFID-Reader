import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InventarioComponent } from './telas/inventario/inventario.component';
import { CadastroComponent } from './telas/cadastro/cadastro.component';
import { HistoricoComponent } from './telas/historico/historico.component';

export const ROUTES: Routes = [
  { path: '', component: InventarioComponent },
  { path: 'inventario', component: InventarioComponent },
  { path: 'cadastro', component: CadastroComponent },
  { path: 'historico', component: HistoricoComponent}
];


