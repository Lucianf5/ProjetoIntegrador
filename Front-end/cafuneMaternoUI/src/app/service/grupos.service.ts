import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Grupos } from '../model/Grupos';

@Injectable({
  providedIn: 'root'
})
export class GruposService {

  constructor(private http: HttpClient) { }

  token = {
    headers: new HttpHeaders().set('Authorization', environment.token)
  }

  getAllGrupos(): Observable<Grupos[]> {
    return this.http.get<Grupos[]>('https://appcafunematerno.herokuapp.com/grupos', this.token)
  }

  postGrupos(grupos: Grupos): Observable<Grupos> {
    return this.http.post<Grupos>('https://appcafunematerno.herokuapp.com/grupos', grupos, this.token)
  }
}
