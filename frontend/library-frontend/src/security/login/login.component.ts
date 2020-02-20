import { FieldMessage } from '../../models/fieldsmessage';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { NotificationService } from '../../util/messages/notification.service';
import { Router } from '@angular/router';
import { Credenciais } from '../../models/credenciais.model';

@Component({
  selector: 'cli-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  creds: Credenciais = {
    email: '',
    senha: ''
};

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    public auth: LoginService
  ) { }

  ngOnInit() {
    this.auth.refreshToken()
      .subscribe(response => {
        this.auth.successfulLogin(response.headers.get('Authorization'));
        this.router.navigate(['/']);
      },
      error => {this.router.navigate(['/login']); });
    this.loginForm = this.formBuilder.group({
      email: this.formBuilder.control('', [Validators.required, Validators.email]),
      password: this.formBuilder.control('', [Validators.required])
    });
  }

  login() {
    this.creds.email = this.loginForm.value.email;
    this.creds.senha = this.loginForm.value.password;
    this.auth.authenticate(this.creds)
    .subscribe(response => {
      this.auth.successfulLogin(response.headers.get('Authorization'));
      this.notificationService.notify(`Bem vindo`);
      this.router.navigate(['/']);
    },
    error => {this.notificationService.notify('Falha de Login : ' + error); });
  }
}
