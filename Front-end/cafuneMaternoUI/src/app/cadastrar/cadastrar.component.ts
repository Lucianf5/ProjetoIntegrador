import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Usuarios } from '../model/Usuarios';
import { AlertasService } from '../service/alertas.service';
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
  show: boolean
  pwdType = 'password'

  constructor(
    private authService: AuthService,
    private router: Router,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
  }

  confirmSenha(event: any) {
    this.confirmarSenha = event.target.value
  }

  tipoUser(event: any) {
    this.tipoUsuario = event.target.value
  }

  cadastrar() {
    this.usuarios.tipo = this.tipoUsuario

    if (this.usuarios.senha != this.confirmarSenha) {
      this.alertas.showAlertDanger('As senhas estão incorretas.')

    } else {
      this.authService.cadastrar(this.usuarios).subscribe((resp: Usuarios) => {
        this.usuarios = resp
        this.router.navigate(['/entrar'])
        this.alertas.showAlertSuccess('Usuário cadastrado com sucesso!')
      })
    }


  }

  showPass() {

    this.show = !this.show
    this.pwdType = this.show ? 'password' : 'text'


  }



}
