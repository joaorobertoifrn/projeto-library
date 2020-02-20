import { ObraService } from './../services/obra.service';
import { AutorService } from './../services/autor.service';
import {NgModule, ModuleWithProviders} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { InputComponent } from './input/input.component';
import { RadioComponent } from './radio/radio.component';
import { LoginService } from './../services/login.service';
import { NotificationService } from './messages/notification.service';
import { BarranotificacaoComponent } from './barranotificacao/barranotificacao.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from '../interceptors/auth.interceptor';
import { StorageService } from '../services/storage.service';
import { DashboardService } from '../services/dashboard.service';
import { UsuarioService } from '../services/usuario.service';

@NgModule({
  declarations: [
    InputComponent,
    RadioComponent,
    BarranotificacaoComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  exports: [
    InputComponent,
    RadioComponent,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BarranotificacaoComponent
  ]
})
export class UtilModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: UtilModule,
      providers: [
        DashboardService,
        AutorService,
        ObraService,
        UsuarioService,
        StorageService,
        LoginService,
        NotificationService,
        {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
      ]
    };
  }
}
