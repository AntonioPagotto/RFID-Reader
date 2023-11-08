import { Component } from '@angular/core';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent {

  nomeItem: string;
  nomeResponsavel: string;
  emailResponsavel: string;

  cadastrar() {
    console.log('Nome do item:', this.nomeItem);
    console.log('Nome do responsável:', this.nomeResponsavel);
    console.log('E-mail do responsável:', this.emailResponsavel);
    // adicione aqui a lógica para cadastrar o item
  }
}