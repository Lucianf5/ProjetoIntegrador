import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Grupos } from 'src/app/model/Grupos';
import { Postagens } from 'src/app/model/Postagens';
import { Usuarios } from 'src/app/model/Usuarios';
import { GruposService } from 'src/app/service/grupos.service';
import { PostagemService } from 'src/app/service/postagem.service';
import { environment } from 'src/environments/environment.prod';


@Component({
  selector: 'app-pagina-grupo',
  templateUrl: './pagina-grupo.component.html',
  styleUrls: ['./pagina-grupo.component.css']
})
export class PaginaGrupoComponent implements OnInit {

  grupo: Grupos = new Grupos()
  usuarios: Usuarios = new Usuarios()
  idUsuario: number
  idGrupo: number
  qtdMembros: number
  postagens: Postagens = new Postagens()
  listaPostagens: Postagens[]

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private grupoService: GruposService,
    private postagemService: PostagemService
    
  ) { }

  ngOnInit()  {
    if(environment.token == '') {
      this.router.navigate(['/home'])
    }
    this.idUsuario = environment.idUserLogin
    this.idGrupo = this.route.snapshot.params['id']
    this.findByIdGrupo(this.idGrupo)
    this.findByUsuario(this.idUsuario)
    // this.qtdMembros = this.grupo.listaParticipantes.length
  }

  findByIdGrupo(id: number) {
      this.grupoService.getById(id).subscribe((resp: Grupos)=>{
      this.grupo = resp
      this.qtdMembros = this.grupo.listaParticipantes.length
    })
  }

  findByUsuario(idUsuario: number){
    return this.grupoService.findByIdUsuario(idUsuario).subscribe((resp: Usuarios)=>{
      this.usuarios = resp
    })
  }

  findAllPostagens(){
    this.grupoService.getAllPostagens().subscribe((resp: Postagens[])=> {
      this.listaPostagens = resp
    })
  }

  sairGrupo(grupo: Grupos) {
    console.log(grupo.listaParticipantes.length)
    this.grupoService.removerGrupo(environment.idUserLogin, grupo.idGrupo ).subscribe((resp: Usuarios)=>{
      this.usuarios = resp
      alert('Removido com sucesso')
      this.router.navigate(['/feed'])
    })
  }

  publicar() {
    this.grupoService.postPostagem(this.postagens, environment.idUserLogin).subscribe((resp: Postagens) => {
      this.postagens = resp
      alert('Postagem cadastrado com sucesso!')
      this.postagens = new Postagens()
    })
    this.listaPostagens
  }

  verificarUser() {
    let ok : boolean = false
    console.log(this.usuarios.tipo)
    if(this.usuarios.tipo == "adm") {
      ok = true
    } else {
      ok = false
    }
    console.log(ok)
    return ok
  }

}
