import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Grupos } from '../model/Grupos';
import { Usuarios } from '../model/Usuarios';

@Injectable({
  providedIn: 'root'
})
export class GruposService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }
  usuario: Usuarios = new Usuarios()

  refreshToken(){
    this.token = {
      headers: new HttpHeaders().set('Authorization',environment.token)
    }
  }

  getAllGrupos(): Observable<Grupos[]> {
    return this.http.get<Grupos[]>('https://appcafunematerno.herokuapp.com/grupos', this.token)
  }

  getById(idGrupo: number): Observable<Grupos>{
    return this.http.get<Grupos>(`https://appcafunematerno.herokuapp.com/grupos/id/${idGrupo}`, this.token)
 }

  postGrupos(grupos: Grupos, id: number): Observable<Grupos> {
    return this.http.post<Grupos>(`https://appcafunematerno.herokuapp.com/grupos/salvar/usuario/${id}`, grupos, this.token)
  }

  addGrupo(idUsuario: number, idGrupo: number): Observable<Usuarios>{
    return this.http.put<Usuarios>(`https://appcafunematerno.herokuapp.com/grupos/id/add/grupo/${idGrupo}/usuario/${idUsuario}`, this.usuario , this.token)
  }


}
