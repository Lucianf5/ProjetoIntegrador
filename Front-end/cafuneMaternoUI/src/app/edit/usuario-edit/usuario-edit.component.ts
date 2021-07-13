import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserLogin } from 'src/app/model/Userlogin';
import { Usuarios } from 'src/app/model/Usuarios';
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
  userLogin: UserLogin = new UserLogin()

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private grupos: GruposService,
    private usuarios: UsuariosService
  ) { }

  ngOnInit() {
    if(environment.token == ''){
      alert('Sua sessão expirou, faça o login novamente')
      this.router.navigate(['/entrar'])
  }
  this.idUser = this.route.snapshot.params['id']
  this.foto = environment.foto
  this.findByUsuario()
  environment.email = this.user.email
  console.log(this.userLogin.email)
  }

  findByUsuario() {
    this.grupos.findByIdUsuario(this.idUser).subscribe((resp: Usuarios) => {
      this.user = resp
    })
  }

  atualizar() {
    console.log(this.user.email)
    this.usuarios.putUsuario(this.user).subscribe((resp: Usuarios)=>{
      this.user = resp
      alert('Usuário atualizado com sucesso!')
      this.router.navigate(['/pagina-usuario', this.idUser])
    })
  }

}
