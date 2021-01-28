import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDepotCountryMapping } from 'app/shared/model/depot-country-mapping.model';

@Component({
  selector: 'jhi-depot-country-mapping-detail',
  templateUrl: './depot-country-mapping-detail.component.html',
})
export class DepotCountryMappingDetailComponent implements OnInit {
  depotCountryMapping: IDepotCountryMapping | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ depotCountryMapping }) => (this.depotCountryMapping = depotCountryMapping));
  }

  previousState(): void {
    window.history.back();
  }
}
