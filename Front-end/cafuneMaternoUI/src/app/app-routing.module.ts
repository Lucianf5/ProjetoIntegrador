import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';

import { DoacoesComponent } from './doacoes/doacoes.component';

import { GrupoEditComponent } from './edit/grupo-edit/grupo-edit.component';

import { EntrarComponent } from './entrar/entrar.component';
import { FeedComponent } from './feed/feed.component';
import { GruposComponent } from './grupos/grupos.component';
import { PaginaGrupoComponent } from './grupos/pagina-grupo/pagina-grupo.component';

import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },


  { path: 'entrar', component: EntrarComponent },
  { path: 'cadastrar', component: CadastrarComponent },
  { path: 'home', component: HomeComponent },
  { path: 'feed', component: FeedComponent },
  { path: 'grupos', component: GruposComponent },

  {path: 'doacoes', component: DoacoesComponent }

  { path: 'pagina-grupo/:id', component: PaginaGrupoComponent},
  { path: 'grupo-edit/:id', component: GrupoEditComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
