import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBusinessUnitConfig } from 'app/shared/model/business-unit-config.model';
import { BusinessUnitConfigService } from './business-unit-config.service';

@Component({
  templateUrl: './business-unit-config-delete-dialog.component.html',
})
export class BusinessUnitConfigDeleteDialogComponent {
  businessUnitConfig?: IBusinessUnitConfig;

  constructor(
    protected businessUnitConfigService: BusinessUnitConfigService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.businessUnitConfigService.delete(id).subscribe(() => {
      this.eventManager.broadcast('businessUnitConfigListModification');
      this.activeModal.close();
    });
  }
}
