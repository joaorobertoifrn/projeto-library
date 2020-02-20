import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Rx';
import { API_CONFIG } from '../config/api.config';
import { Autor } from '../models/autor.model';
import { StorageService } from './storage.service';

@Injectable()
export class AutorService {
  constructor(public http: HttpClient, public storage: StorageService) {}

  findAll(): Observable<Autor[]> {
    return this.http.get<Autor[]>(`${API_CONFIG.baseUrl}/autores`);
  }

  findById(id: string) {
    return this.http.get<Autor>(`${API_CONFIG.baseUrl}/autores/${id}`);
  }

  insert(obj: Autor) {
    return this.http.post(`${API_CONFIG.baseUrl}/autores`, obj, {
      observe: 'response',
      responseType: 'text'
    });
  }

  delete(id: string) {
    return this.http.delete(`${API_CONFIG.baseUrl}/autores/${id}`, {
      observe: 'response',
      responseType: 'text'
    });
  }

}
