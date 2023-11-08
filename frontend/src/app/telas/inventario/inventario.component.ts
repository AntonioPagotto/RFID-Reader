import { Component } from '@angular/core';
import { MainService } from '../../services/main.service';
import { TagInfo } from 'src/app/models/tagInfo.model';

@Component({
  selector: 'inventario-component',
  templateUrl: './inventario.component.html',
  styleUrls: []
})
export class InventarioComponent {

  tags : TagInfo[] = []

  constructor(private mainService: MainService) {}

  ngOnInit() {
    this.mainService.getAllTags().subscribe((tags: any) => {
      this.tags = tags;
    });
  }


}