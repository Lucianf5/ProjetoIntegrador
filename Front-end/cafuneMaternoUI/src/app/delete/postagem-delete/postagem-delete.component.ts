import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Postagens } from 'src/app/model/Postagens';
import { GruposService } from 'src/app/service/grupos.service';
import { PostagemService } from 'src/app/service/postagem.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-postagem-delete',
  templateUrl: './postagem-delete.component.html',
  styleUrls: ['./postagem-delete.component.css']
})
export class PostagemDeleteComponent implements OnInit {

  idPostagem : number
  postagens : Postagens = new Postagens()

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private postagemService: PostagemService,
    private gruposService: GruposService

  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    if (environment.token == '') {
      this.router.navigate(['/home'])
    }
    this.gruposService.refreshToken()
    this.idPostagem = this.route.snapshot.params['id']
    this.findByIdPostagem(this.idPostagem)
    
  }

  apagar() {

  }

  findByIdPostagem(id: number){
    this.postagemService.getById(id).subscribe((resp: Postagens) => {
      this.postagens = resp
    })
  }

}
