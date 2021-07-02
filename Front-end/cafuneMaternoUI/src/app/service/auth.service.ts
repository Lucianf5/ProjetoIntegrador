import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { UserLogin } from '../model/UserLogin';
import { Usuarios } from '../model/Usuarios';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  entrar(userLogin: UserLogin): Observable<UserLogin>{
    return this.http.post<UserLogin>('https://cafunematerno.herokuapp.com/usuarios/logar', userLogin)
  }

  cadastrar(user: Usuarios): Observable<Usuarios>{
    return this.http.post<Usuarios>('https://cafunematerno.herokuapp.com/usuarios/cadastrar', user)
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
