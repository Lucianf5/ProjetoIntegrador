import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Postagens } from 'src/app/model/Postagens';
import { AlertasService } from 'src/app/service/alertas.service';
import { GruposService } from 'src/app/service/grupos.service';
import { PostagemService } from 'src/app/service/postagem.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-postagem-edit',
  templateUrl: './postagem-edit.component.html',
  styleUrls: ['./postagem-edit.component.css']
})
export class PostagemEditComponent implements OnInit {


  postagens : Postagens = new Postagens()
  idPostagem : number
  idUser: number

  constructor(
    private gruposService: GruposService,
    private router: Router,
    private route: ActivatedRoute,
    private postagemService: PostagemService,
    private alertas: AlertasService

  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    if (environment.token == '') {
      this.router.navigate(['/home'])
      this.alertas.showAertInfo('É necessário logar novamente')
    }
    this.gruposService.refreshToken()
    this.idPostagem = this.route.snapshot.params['id']
    this.findByIdPostagem(this.idPostagem)
    this.idUser = environment.idUserLogin

  }

  findByIdPostagem(id: number){
    this.postagemService.getById(id).subscribe((resp: Postagens) => {
      this.postagens = resp
    })
  }

  atualizar(){
    this.postagemService.putPostagem(this.postagens, this.idPostagem).subscribe((resp: Postagens) => {
      this.postagens = resp
      this.alertas.showAlertSuccess('Postagem atualizada com sucesso')
      this.router.navigate(['/minhas-postagens',this.idUser])
    })
  }

}