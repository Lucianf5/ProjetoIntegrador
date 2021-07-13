import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Grupos } from '../model/Grupos';
import { Postagens } from '../model/Postagens';
import { Usuarios } from '../model/Usuarios';
import { GruposService } from '../service/grupos.service';
@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {
  postagens: Postagens = new Postagens()
  grupos: Grupos = new Grupos()
  usuarios: Usuarios = new Usuarios()
  listaGrupos: Grupos[]
  listaPostagens: Postagens[]
  idUsuario = environment.idUserLogin
  constructor(
    private router: Router,
    private gruposService: GruposService
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    if (environment.token == '') {
      this.router.navigate(['/home'])
    }
    this.gruposService.refreshToken()
    this.findAllGrupos()
  }


  findAllGrupos() {
    this.gruposService.getAllGrupos().subscribe((resp: Grupos[]) => {
      this.listaGrupos = resp
    })
  }

  findAllPostagens() {
    this.gruposService.getAllPostagens().subscribe((resp: Postagens[]) => {
      this.listaPostagens = resp
    })
  }


  cadastrar() {
    this.gruposService.postGrupos(this.grupos, environment.idUserLogin).subscribe((resp: Grupos) => {
      this.grupos = resp
      alert('Grupo cadastrado com sucesso!')
      this.grupos = new Grupos()
    })
    this.findAllGrupos()
  }

  postar() {
    this.gruposService.postPostagem(this.postagens, environment.idUserLogin).subscribe((resp: Postagens) => {
      this.postagens = resp
      alert('Postagem cadastrado com sucesso!')
      this.postagens = new Postagens()
    })
    this.listaPostagens
  }

  entrarGrupo(grupo: Grupos) {
    console.log(grupo.idGrupo)
    this.gruposService.addGrupo(environment.idUserLogin, grupo.idGrupo).subscribe((resp: Usuarios) => {
      this.usuarios = resp
      alert('Adicionado com sucesso')
    })
  }


  verificarUser() {

    let ok: boolean = false
    if (this.usuarios.tipo == "adm") {
      ok = true
    } else {
      ok = false
    }
    return ok
  }

  verificaUsuarioGrupo(grupo: Grupos) {
    let ok: boolean = true
    //console.log(this.usuarios.listaGrupos.includes(grupo))
    //return this.usuarios.listaGrupos.includes(grupo)
    for (let i = 0; i < this.usuarios.listaGrupos.length; i++) {
      if (this.usuarios.listaGrupos[i].idGrupo == grupo.idGrupo) {
        ok = false
        return ok
      }
    }
    return ok
  }


  deleteGrupo(grupo: Grupos) {
    //console.log(grupo.listaParticipantes.length)
    if (grupo.listaParticipantes.length == 0) {
      alert("Grupo apagado com sucesso")
      this.gruposService.deleteGrupos(grupo.idGrupo)
      this.findAllGrupos()
    } else {
      alert("Não é possível exclir um grupo com membros ativos")

    }
  }




  findUsuarioId() {
    return this.gruposService.findByIdUsuario(this.idUsuario).subscribe((resp: Usuarios) => {
      this.usuarios = resp
    })
  }
}




