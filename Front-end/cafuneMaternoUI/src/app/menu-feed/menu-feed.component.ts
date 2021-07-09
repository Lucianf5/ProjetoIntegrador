import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-menu-feed',
  templateUrl: './menu-feed.component.html',
  styleUrls: ['./menu-feed.component.css']
})
export class MenuFeedComponent implements OnInit {

  nome = environment.nome
  foto = environment.foto

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    
    }
    sair() {
      this.router.navigate(['/entrar'])
      environment.token =  ''
      environment.nome = ''
      environment.foto = ''
      environment.idUserLogin = 0
  }

}
