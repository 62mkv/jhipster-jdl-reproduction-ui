import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRandomNewEntity } from 'app/shared/model/random-new-entity.model';

@Component({
  selector: 'jhi-random-new-entity-detail',
  templateUrl: './random-new-entity-detail.component.html',
})
export class RandomNewEntityDetailComponent implements OnInit {
  randomNewEntity: IRandomNewEntity | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ randomNewEntity }) => (this.randomNewEntity = randomNewEntity));
  }

  previousState(): void {
    window.history.back();
  }
}
