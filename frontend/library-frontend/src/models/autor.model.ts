import { Pais } from './pais.model';
import { Obra } from './obra.model';

export interface Autor {

  id: number;
  nome: string;
  sexo: string;
  dtNascimento: string;
  cpf: string;
  email: string;
}
