import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBusinessUnitConfig } from 'app/shared/model/business-unit-config.model';

@Component({
  selector: 'jhi-business-unit-config-detail',
  templateUrl: './business-unit-config-detail.component.html',
})
export class BusinessUnitConfigDetailComponent implements OnInit {
  businessUnitConfig: IBusinessUnitConfig | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ businessUnitConfig }) => (this.businessUnitConfig = businessUnitConfig));
  }

  previousState(): void {
    window.history.back();
  }
}
