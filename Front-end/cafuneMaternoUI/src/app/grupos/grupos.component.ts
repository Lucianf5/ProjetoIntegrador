import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Grupos } from '../model/Grupos';
import { GruposService } from '../service/grupos.service';

@Component({
  selector: 'app-grupos',
  templateUrl: './grupos.component.html',
  styleUrls: ['./grupos.component.css']
})
export class GruposComponent implements OnInit {

  grupos: Grupos = new Grupos()
  listaGrupos: Grupos[]

  constructor(
    private router: Router,
    private gruposService: GruposService
  ) { }

  ngOnInit() {
    if(environment.token == '') {
      this.router.navigate(['/entrar'])
    }

    this.findAllGrupos()
  }

  findAllGrupos(){
    this.gruposService.getAllGrupos().subscribe((resp: Grupos[])=> {
      this.listaGrupos = resp
    })
  }

  cadastrar() {
    this.gruposService.postGrupos(this.grupos).subscribe((resp: Grupos) => {
      this.grupos = resp
      alert('Grupo cadastrado comm sucesso!')
      this.findAllGrupos()
      this.grupos = new Grupos()
    })
  }

}
