import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRandomNewEntity } from 'app/shared/model/random-new-entity.model';
import { RandomNewEntityService } from './random-new-entity.service';

@Component({
  templateUrl: './random-new-entity-delete-dialog.component.html',
})
export class RandomNewEntityDeleteDialogComponent {
  randomNewEntity?: IRandomNewEntity;

  constructor(
    protected randomNewEntityService: RandomNewEntityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.randomNewEntityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('randomNewEntityListModification');
      this.activeModal.close();
    });
  }
}
