import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { GruposService } from '../service/grupos.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(
   private router: Router,
   private gruposService: GruposService
  ) { }

  ngOnInit(){
      window.scroll(0,0)
      if (environment.token == '') {
        this.router.navigate(['/home'])
      }
      this.gruposService.refreshToken()
  }

}
