import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBusinessUnitConfig, BusinessUnitConfig } from 'app/shared/model/business-unit-config.model';
import { BusinessUnitConfigService } from './business-unit-config.service';

@Component({
  selector: 'jhi-business-unit-config-update',
  templateUrl: './business-unit-config-update.component.html',
})
export class BusinessUnitConfigUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    unitName: [null, [Validators.required, Validators.minLength(10), Validators.maxLength(32)]],
    defaultLabelQueue: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(16)]],
  });

  constructor(
    protected businessUnitConfigService: BusinessUnitConfigService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ businessUnitConfig }) => {
      this.updateForm(businessUnitConfig);
    });
  }

  updateForm(businessUnitConfig: IBusinessUnitConfig): void {
    this.editForm.patchValue({
      id: businessUnitConfig.id,
      unitName: businessUnitConfig.unitName,
      defaultLabelQueue: businessUnitConfig.defaultLabelQueue,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const businessUnitConfig = this.createFromForm();
    if (businessUnitConfig.id !== undefined) {
      this.subscribeToSaveResponse(this.businessUnitConfigService.update(businessUnitConfig));
    } else {
      this.subscribeToSaveResponse(this.businessUnitConfigService.create(businessUnitConfig));
    }
  }

  private createFromForm(): IBusinessUnitConfig {
    return {
      ...new BusinessUnitConfig(),
      id: this.editForm.get(['id'])!.value,
      unitName: this.editForm.get(['unitName'])!.value,
      defaultLabelQueue: this.editForm.get(['defaultLabelQueue'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBusinessUnitConfig>>): void {
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
