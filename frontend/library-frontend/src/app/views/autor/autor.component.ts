import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../../../services/login.service';
import { NotificationService } from '../../../util/messages/notification.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Autor } from '../../../models/autor.model';
import { AutorService } from '../../../services/autor.service';

@Component({
  selector: 'cli-autor',
  templateUrl: './autor.component.html'
})
export class AutorComponent implements OnInit {
  // lista de Autores
  public autores: Autor[] = [];
  public QtdAutores: number;
  autor: Autor;
  autorForm: FormGroup;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    public auth: LoginService,
    public autorService: AutorService
  ) {
    this.autorForm = this.formBuilder.group({
      id: ['', []],
      nome: ['', [Validators.required]],
      email: ['', [Validators.email]],
      sexo: ['', [Validators.required]],
      cpf: ['', [Validators.minLength(11), Validators.maxLength(14)]],
      dtNascimento: ['',[]]
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
    this.autorService.findAll().subscribe(
      response => {
        this.autores = response;
        this.QtdAutores = this.autores.length;
      },
      error => {}
    );
  }

  cadastrarAutor() {
    this.autorService.insert(this.autorForm.value).subscribe(
      response => {
        this.notificationService.notify('Autor Cadastrado com Sucesso');
        this.loadData();
      },
      error => {
        this.notificationService.notify('Falha ao Cadastrar Autor : ' + error);
      }
    );
  }

  deletarAutor(autor_id) {
    this.autorService.delete(autor_id).subscribe(
      response => {
        this.notificationService.notify('Autor Deletado com Sucesso');
        this.loadData();
      },
      error => {
        this.notificationService.notify('Falha ao Deletar Autor : ' + error);
      }
    );
  }

  carregarAutor(autor_id) {
    if (autor_id) {
      this.autorService.findById(autor_id).subscribe(
        response => {
          this.autor = response;
          if (this.autor) {
            this.autorForm.controls.id.setValue(this.autor.id);
            this.autorForm.controls.nome.setValue(this.autor.nome);
            this.autorForm.controls.email.setValue(this.autor.email);
            this.autorForm.controls.dtNascimento.setValue(this.autor.dtNascimento);
            this.autorForm.controls.cpf.setValue(this.autor.cpf);
            this.autorForm.controls.sexo.setValue(this.autor.sexo);
          }
        },
        error => {}
      );
    }
  }
}
