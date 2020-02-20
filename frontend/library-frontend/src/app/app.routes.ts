import { ObraComponent } from './views/obra/obra.component';
import { LoginComponent } from './../security/login/login.component';
import { Routes } from '@angular/router';
import { HomeComponent } from './template/home/home.component';
import { UsuarioComponent } from './views/usuario/usuario.component';
import { AutorComponent } from './views/autor/autor.component';

export const ROUTES: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login', component: LoginComponent},
    { path: 'autor', component: AutorComponent },
    { path: 'obra', component: ObraComponent },
    { path: 'usuario', component: UsuarioComponent }
];
