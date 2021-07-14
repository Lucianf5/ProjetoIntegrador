import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserLogin } from 'src/app/model/Userlogin';
import { Usuarios } from 'src/app/model/Usuarios';
import { AlertasService } from 'src/app/service/alertas.service';
import { AuthService } from 'src/app/service/auth.service';
import { GruposService } from 'src/app/service/grupos.service';
import { UsuariosService } from 'src/app/service/usuarios.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-usuario-edit',
  templateUrl: './usuario-edit.component.html',
  styleUrls: ['./usuario-edit.component.css']
})
export class UsuarioEditComponent implements OnInit {

  user: Usuarios = new Usuarios()
  foto: string
  idUser: number

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private grupos: GruposService,
    private usuarios: UsuariosService,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
    if(environment.token == ''){
      this.alertas.showAertInfo('Sua sessão expirou, faça o login novamente')
      this.router.navigate(['/entrar'])
  }
  this.idUser = this.route.snapshot.params['id']
  this.foto = environment.foto
  this.findByUsuario()
  environment.email = this.user.email

  }

  findByUsuario() {
    this.grupos.findByIdUsuario(this.idUser).subscribe((resp: Usuarios) => {
      this.user = resp
    })
  }

  atualizar() {
    console.log(this.user)
    this.usuarios.putUsuario(this.user).subscribe((resp: Usuarios) => {
        this.user = resp
        this.router.navigate(['/inicio'])
        this.alertas.showAlertSuccess('Usuário atualizado com sucesso, faça o login novamente.')
        environment.token = ''
        environment.nome = ''
        environment.foto = ''
        environment.email = ''
  })

  }

}