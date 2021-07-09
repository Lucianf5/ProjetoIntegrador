import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-doacoes',
  templateUrl: './doacoes.component.html',
  styleUrls: ['./doacoes.component.css']
})
export class DoacoesComponent implements OnInit {

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    if(environment.token == '') {
      this.router.navigate(['/entrar'])
    }
  }

}
