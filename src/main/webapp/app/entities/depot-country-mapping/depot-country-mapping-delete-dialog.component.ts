import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDepotCountryMapping } from 'app/shared/model/depot-country-mapping.model';
import { DepotCountryMappingService } from './depot-country-mapping.service';

@Component({
  templateUrl: './depot-country-mapping-delete-dialog.component.html',
})
export class DepotCountryMappingDeleteDialogComponent {
  depotCountryMapping?: IDepotCountryMapping;

  constructor(
    protected depotCountryMappingService: DepotCountryMappingService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.depotCountryMappingService.delete(id).subscribe(() => {
      this.eventManager.broadcast('depotCountryMappingListModification');
      this.activeModal.close();
    });
  }
}
