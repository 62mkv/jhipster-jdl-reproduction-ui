import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDepotCountryMapping, DepotCountryMapping } from 'app/shared/model/depot-country-mapping.model';
import { DepotCountryMappingService } from './depot-country-mapping.service';

@Component({
  selector: 'jhi-depot-country-mapping-update',
  templateUrl: './depot-country-mapping-update.component.html',
})
export class DepotCountryMappingUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    depotName: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(10)]],
    countryCode: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(2)]],
  });

  constructor(
    protected depotCountryMappingService: DepotCountryMappingService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ depotCountryMapping }) => {
      this.updateForm(depotCountryMapping);
    });
  }

  updateForm(depotCountryMapping: IDepotCountryMapping): void {
    this.editForm.patchValue({
      id: depotCountryMapping.id,
      depotName: depotCountryMapping.depotName,
      countryCode: depotCountryMapping.countryCode,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const depotCountryMapping = this.createFromForm();
    if (depotCountryMapping.id !== undefined) {
      this.subscribeToSaveResponse(this.depotCountryMappingService.update(depotCountryMapping));
    } else {
      this.subscribeToSaveResponse(this.depotCountryMappingService.create(depotCountryMapping));
    }
  }

  private createFromForm(): IDepotCountryMapping {
    return {
      ...new DepotCountryMapping(),
      id: this.editForm.get(['id'])!.value,
      depotName: this.editForm.get(['depotName'])!.value,
      countryCode: this.editForm.get(['countryCode'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepotCountryMapping>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
