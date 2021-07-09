import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Grupos } from 'src/app/model/Grupos';
import { Usuarios } from 'src/app/model/Usuarios';
import { GruposService } from 'src/app/service/grupos.service';
import { environment } from 'src/environments/environment.prod';


@Component({
  selector: 'app-pagina-grupo',
  templateUrl: './pagina-grupo.component.html',
  styleUrls: ['./pagina-grupo.component.css']
})
export class PaginaGrupoComponent implements OnInit {

  grupo: Grupos = new Grupos()
  usuarios: Usuarios = new Usuarios()
  idGrupo: number
  qtdMembros: number
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private grupoService: GruposService
  ) { }

  ngOnInit()  {
    if(environment.token == '') {
      this.router.navigate(['/entrar'])
    }
    this.idGrupo = this.route.snapshot.params['id']
    this.findByIdGrupo(this.idGrupo)
    // this.qtdMembros = this.grupo.listaParticipantes.length
  }

  findByIdGrupo(id: number) {
    this.grupoService.getById(id).subscribe((resp: Grupos)=>{
      this.grupo = resp
      this.qtdMembros = this.grupo.listaParticipantes.length
    })
  }

  updateGrupo() {
      this.grupoService.putGrupos(this.grupo, this.idGrupo).subscribe((resp: Grupos)=>{
        this.grupo = resp
        alert("Grupo Atualizado")
        this.findByIdGrupo(this.idGrupo)
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


}
