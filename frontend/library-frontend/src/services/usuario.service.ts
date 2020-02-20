import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Rx';
import { API_CONFIG } from '../config/api.config';
import { Usuario } from '../models/usuario.model';
import { StorageService } from './storage.service';

@Injectable()
export class UsuarioService {
  constructor(public http: HttpClient, public storage: StorageService) {}

  findAll(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${API_CONFIG.baseUrl}/usuarios`);
  }

  findById(id: string) {
    return this.http.get<Usuario>(`${API_CONFIG.baseUrl}/usuarios/${id}`);
  }

  insert(obj: Usuario) {
    return this.http.post(`${API_CONFIG.baseUrl}/usuarios`, obj, {
      observe: 'response',
      responseType: 'text'
    });
  }

  delete(id: string) {
    return this.http.delete(`${API_CONFIG.baseUrl}/usuarios/${id}`, {
      observe: 'response',
      responseType: 'text'
    });
  }

}
