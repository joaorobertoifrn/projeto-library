import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../../../services/login.service';
import { NotificationService } from '../../../util/messages/notification.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../../models/usuario.model';
import { UsuarioService } from '../../../services/usuario.service';

@Component({
  selector: 'cli-usuario',
  templateUrl: './usuario.component.html'
})
export class UsuarioComponent implements OnInit {
  // lista de Usuarios
  public usuarios: Usuario[] = [];
  public QtdUsuarios: number;
  usuario: Usuario;
  usuarioForm: FormGroup;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    public auth: LoginService,
    public usuarioService: UsuarioService
  ) {
    this.usuarioForm = this.formBuilder.group({
      id: ['', []],
      nome: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.auth.refreshToken().subscribe(
      response => {
        this.auth.successfulLogin(response.headers.get('Authorization'));
        this.loadData();
      },
      error => {
        this.router.navigate(['/login']);
      }
    );
  }

  loadData() {
    this.usuarioService.findAll().subscribe(
      response => {
        this.usuarios = response;
        this.QtdUsuarios = this.usuarios.length;
      },
      error => {}
    );
  }

  cadastrarUsuario() {
    this.usuarioService.insert(this.usuarioForm.value).subscribe(
      response => {
        this.notificationService.notify('Usuário Cadastrado com Sucesso');
        this.loadData();
      },
      error => {
        this.notificationService.notify('Falha ao Cadastrar Usuário : ' + error);
      }
    );
  }

  deletarUsuario(usuario_id) {
    this.usuarioService.delete(usuario_id).subscribe(
      response => {
        this.notificationService.notify('Usuário Deletado com Sucesso');
        this.loadData();
      },
      error => {
        this.notificationService.notify('Falha ao Deletar o Usuário : ' + error);
      }
    );
  }

  carregarUsuario(usuario_id) {
    if (usuario_id) {
      this.usuarioService.findById(usuario_id).subscribe(
        response => {
          this.usuario = response;
          if (this.usuario) {
            this.usuarioForm.controls.id.setValue(this.usuario.id);
            this.usuarioForm.controls.nome.setValue(this.usuario.nome);
            this.usuarioForm.controls.email.setValue(this.usuario.email);
            this.usuarioForm.controls.senha.setValue(this.usuario.senha);
          }
        },
        error => {}
      );
    }
  }
}
