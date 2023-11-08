import { Component } from '@angular/core';
import { MainService } from '../../services/main.service';

@Component({
  selector: 'historico-component',
  templateUrl: './historico.component.html',
  styleUrls: []
})
export class HistoricoComponent {

  tags = [
    { TAGID: 1, Nome: 'Tag 1', Responsavel: 'João', EmailResponsavel: 'joao@email.com',
      Localizacao: 'São Paulo', Horario: '08:00 - 17:00' },
    { TAGID: 2, Nome: 'Tag 2', Responsavel: 'Maria', EmailResponsavel: 'maria@email.com',
      Localizacao: 'Rio de Janeiro', Horario: '09:00 - 18:00' },
    // adicione mais tags aqui
  ];
}