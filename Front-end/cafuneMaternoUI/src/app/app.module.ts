import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { RodapeComponent } from './rodape/rodape.component';
import { HomeComponent } from './home/home.component';
import { EntrarComponent } from './entrar/entrar.component';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { FeedComponent } from './feed/feed.component';
import { GruposComponent } from './grupos/grupos.component';
import { PaginaGrupoComponent } from './grupos/pagina-grupo/pagina-grupo.component';
import { GrupoEditComponent } from './edit/grupo-edit/grupo-edit.component';
import { MenuFeedComponent } from './menu-feed/menu-feed.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    RodapeComponent,
    HomeComponent,
    EntrarComponent,
    CadastrarComponent,
    FeedComponent,
    GruposComponent,
    PaginaGrupoComponent,
    GrupoEditComponent,
    MenuFeedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
