import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Rx';
import { API_CONFIG } from '../config/api.config';
import { Obra } from '../models/obra.model';
import { StorageService } from './storage.service';

@Injectable()
export class ObraService {
  constructor(public http: HttpClient, public storage: StorageService) {}

  findAll(): Observable<Obra[]> {
    return this.http.get<Obra[]>(`${API_CONFIG.baseUrl}/obras`);
  }

  findById(id: string) {
    return this.http.get<Obra>(`${API_CONFIG.baseUrl}/obras/${id}`);
  }

  insert(obj: Obra) {
    return this.http.post(`${API_CONFIG.baseUrl}/obras`, obj, {
      observe: 'response',
      responseType: 'text'
    });
  }

  delete(id: string) {
    return this.http.delete(`${API_CONFIG.baseUrl}/obras/${id}`, {
      observe: 'response',
      responseType: 'text'
    });
  }

}
