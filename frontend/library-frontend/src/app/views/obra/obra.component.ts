import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../../../services/login.service';
import { NotificationService } from '../../../util/messages/notification.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Obra } from '../../../models/obra.model';
import { ObraService } from '../../../services/obra.service';

@Component({
  selector: 'cli-obra',
  templateUrl: './obra.component.html'
})
export class ObraComponent implements OnInit {
  // lista de Obras
  public obras: Obra[] = [];
  public QtdObras: number;
  obra: Obra;
  obraForm: FormGroup;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private notificationService: NotificationService,
    public auth: LoginService,
    public obraService: ObraService
  ) {
    this.obraForm = this.formBuilder.group({
      id: ['', []],
      nome: ['', [Validators.required]],
      descricao: ['', [Validators.required, Validators.maxLength(240)]],
      dtPublicacao: ['', [Validators.required]],
      dtExposicao: ['', [Validators.required]]
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
    this.obraService.findAll().subscribe(
      response => {
        this.obras = response;
        this.QtdObras = this.obras.length;
      },
      error => {}
    );
  }

  cadastrarObra() {
    this.obraService.insert(this.obraForm.value).subscribe(
      response => {
        this.notificationService.notify('Obra Cadastrado com Sucesso');
        this.loadData();
      },
      error => {
        this.notificationService.notify('Falha ao Cadastrar Obra : ' + error);
      }
    );
  }

  deletarObra(obra_id) {
    this.obraService.delete(obra_id).subscribe(
      response => {
        this.notificationService.notify('Obra Deletado com Sucesso');
        this.loadData();
      },
      error => {
        this.notificationService.notify('Falha ao Deletar Obra : ' + error);
      }
    );
  }

  carregarObra(obra_id) {
    if (obra_id) {
      this.obraService.findById(obra_id).subscribe(
        response => {
          this.obra = response;
          if (this.obra) {
            this.obraForm.controls.id.setValue(this.obra.id);
            this.obraForm.controls.nome.setValue(this.obra.nome);
            this.obraForm.controls.descricao.setValue(this.obra.descricao);
            this.obraForm.controls.dtPublicacao.setValue(this.obra.dtPublicacao);
            this.obraForm.controls.dtExposicao.setValue(this.obra.dtExposicao);
          }
        },
        error => {}
      );
    }
  }
}
