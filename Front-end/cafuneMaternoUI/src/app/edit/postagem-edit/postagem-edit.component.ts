import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Postagens } from 'src/app/model/Postagens';
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

  constructor(
    private gruposService: GruposService,
    private router: Router,
    private route: ActivatedRoute,
    private postagemService: PostagemService
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    if (environment.token == '') {
      this.router.navigate(['/home'])
    }
    this.gruposService.refreshToken()
    this.idPostagem = this.route.snapshot.params['id']
    console.log(this.idPostagem)
    this.findByIdPostagem(this.idPostagem)
    console.log(this.postagens)
  }

  findByIdPostagem(id: number){
    this.postagemService.getById(id).subscribe((resp: Postagens) => {
      this.postagens = resp
    })
    console.log(this.postagens)
  }

  atualizar(){

  }

}
