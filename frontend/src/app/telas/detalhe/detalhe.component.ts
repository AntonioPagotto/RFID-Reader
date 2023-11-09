import { Component } from '@angular/core';
import { MainService } from '../../services/main.service';
import { TagInfo } from 'src/app/models/tagInfo.model';
import { ActivatedRoute, Route } from '@angular/router';

@Component({
  selector: 'detalhe-component',
  templateUrl: './detalhe.component.html',
  styleUrls: []
})
export class DetalheComponent {

  tags : TagInfo[] = []
  id: string;

  constructor(private mainService: MainService,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id')!;
    this.mainService.getTagDetail(this.id).subscribe((tags: any) => {
      this.tags = tags;
    });
  }

}