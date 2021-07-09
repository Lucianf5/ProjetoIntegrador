import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuarios } from '../model/Usuarios';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {

  usuarios: Usuarios = new Usuarios()
  confirmarSenha: string
  tipoUsuario: string

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    window.scroll(0,0)
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value
  }

  tipoUser(event: any) {
    this.tipoUsuario = event.target.value
  }

  cadastrar() {
    this.usuarios.tipo = this.tipoUsuario

    if(this.usuarios.senha != this.confirmarSenha) {
      alert('As senhas estão incorretas.')
    } else {
      this.authService.cadastrar(this.usuarios).subscribe((resp: Usuarios) => {
        this.usuarios = resp
        this.router.navigate(['/logar'])
        alert('Usuário cadastrado com sucesso!')
      })
    }
  }


}
