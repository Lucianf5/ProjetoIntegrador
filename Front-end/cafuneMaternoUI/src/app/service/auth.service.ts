import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserLogin } from '../model/UserLogin';
import { Usuarios } from '../model/Usuarios';
import { GruposService } from './grupos.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {



  constructor(
    private http: HttpClient,
    private grupoService: GruposService
  ) { }


  entrar(userLogin: UserLogin): Observable<UserLogin>{
    return this.http.post<UserLogin>('https://appcafunematerno.herokuapp.com/usuarios/logar', userLogin)
  }

  cadastrar(user: Usuarios): Observable<Usuarios>{
    return this.http.post<Usuarios>('https://appcafunematerno.herokuapp.com/usuarios/cadastrar', user)
  }



  //TODO: Verificar se é necessário
  /*logado() {
    let ok : boolean = false

    if(environment.token != '') {
        ok = true
    }
    return ok
  }*/

}
