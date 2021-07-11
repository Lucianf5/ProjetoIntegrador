import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Grupos } from '../model/Grupos';
import { Usuarios } from '../model/Usuarios';
import { AuthService } from '../service/auth.service';
import { GruposService } from '../service/grupos.service';
@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {
  grupos: Grupos = new Grupos()
  usuarios: Usuarios = new Usuarios()
  listaGrupos: Grupos[]
  idUsuario = environment.idUserLogin
  constructor(
    private router: Router,
    private gruposService: GruposService
  ) { }

  ngOnInit() {
    if(environment.token == '') {
      this.router.navigate(['/entrar'])
    }
    this.gruposService.refreshToken()
    this.findAllGrupos()
  }


  findAllGrupos(){
    this.gruposService.getAllGrupos().subscribe((resp: Grupos[])=> {
      this.listaGrupos = resp
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

  entrarGrupo(grupo: Grupos) {
    console.log(grupo.idGrupo)
    this.gruposService.addGrupo(environment.idUserLogin, grupo.idGrupo ).subscribe((resp : Usuarios)=>{
      this.usuarios = resp
      alert('Adicionado com sucesso')
    })
  }


  verificarUser() {
    let ok : boolean = false
    if(this.usuarios.tipo == "adm") {
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
  for(let i = 0; i < this.usuarios.listaGrupos.length; i++) {
    if(this.usuarios.listaGrupos[i].idGrupo == grupo.idGrupo) {
      ok = false
      return ok
    }
  }
  return ok
 }

 deleteGrupo(grupo: Grupos) {
  console.log(grupo.listaParticipantes.length)
  if(grupo.listaParticipantes.length == 0) {
    alert("Grupo apagado com sucesso")
    this.gruposService.deleteGrupos(grupo.idGrupo)
    this.findAllGrupos()
  } else {
    alert("Não é possível exclir um grupo com membros ativos")
  }
}


 findUsuarioId() {
    return this.gruposService.findByIdUsuario(this.idUsuario).subscribe((resp: Usuarios)=>{
      this.usuarios = resp
    })
 }

}
