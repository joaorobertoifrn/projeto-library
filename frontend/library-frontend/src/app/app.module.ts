import { AutorComponent } from './views/autor/autor.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ROUTES } from './app.routes';
import { AppComponent } from './app.component';
import { HeaderComponent } from './template/header/header.component';
import { FooterComponent } from './template/footer/footer.component';
import { SidemenuComponent } from './template/sidemenu/sidemenu.component';
import { HomeComponent } from './template/home/home.component';
import { UsuarioComponent } from './views/usuario/usuario.component';
import { RouterModule, PreloadAllModules } from '@angular/router';
import { LoginComponent } from './../security/login/login.component';
import { UtilModule } from './../util/util.module';
import { ObraComponent } from './views/obra/obra.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SidemenuComponent,
    HomeComponent,
    AutorComponent,
    ObraComponent,
    UsuarioComponent,
    LoginComponent
    ],
  imports: [
    BrowserModule,
    HttpClientModule,
    UtilModule.forRoot(),
    RouterModule.forRoot(ROUTES, {preloadingStrategy: PreloadAllModules, onSameUrlNavigation: 'reload'})
  ],
  providers: [{provide: LOCALE_ID, useValue: 'pt-BR'}],
  bootstrap: [AppComponent]
})
export class AppModule { }
