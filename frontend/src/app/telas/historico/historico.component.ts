import { Component } from '@angular/core';
import { MainService } from '../../services/main.service';
import { TagInfo } from 'src/app/models/tagInfo.model';

@Component({
  selector: 'historico-component',
  templateUrl: './historico.component.html',
  styleUrls: []
})
export class HistoricoComponent {

  tags : TagInfo[] = []

  constructor(private mainService: MainService) {}

  ngOnInit() {
    this.mainService.getTagsHistory().subscribe((tags: any) => {
      this.tags = tags;
    });
  }

}