import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../services/login.service';
import { Router } from '@angular/router';
import { Usuario } from '../../../models/usuario.model';

@Component({
  selector: 'cli-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  usuario: Usuario;


  constructor(private router: Router, public auth: LoginService) { }

  ngOnInit() {
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
