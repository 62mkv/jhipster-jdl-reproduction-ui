import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRandomNewEntity, RandomNewEntity } from 'app/shared/model/random-new-entity.model';
import { RandomNewEntityService } from './random-new-entity.service';

@Component({
  selector: 'jhi-random-new-entity-update',
  templateUrl: './random-new-entity-update.component.html',
})
export class RandomNewEntityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    entityName: [null, [Validators.required, Validators.minLength(10), Validators.maxLength(32)]],
    someValue: [null, [Validators.required, Validators.minLength(1), Validators.maxLength(16)]],
  });

  constructor(
    protected randomNewEntityService: RandomNewEntityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ randomNewEntity }) => {
      this.updateForm(randomNewEntity);
    });
  }

  updateForm(randomNewEntity: IRandomNewEntity): void {
    this.editForm.patchValue({
      id: randomNewEntity.id,
      entityName: randomNewEntity.entityName,
      someValue: randomNewEntity.someValue,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const randomNewEntity = this.createFromForm();
    if (randomNewEntity.id !== undefined) {
      this.subscribeToSaveResponse(this.randomNewEntityService.update(randomNewEntity));
    } else {
      this.subscribeToSaveResponse(this.randomNewEntityService.create(randomNewEntity));
    }
  }

  private createFromForm(): IRandomNewEntity {
    return {
      ...new RandomNewEntity(),
      id: this.editForm.get(['id'])!.value,
      entityName: this.editForm.get(['entityName'])!.value,
      someValue: this.editForm.get(['someValue'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRandomNewEntity>>): void {
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
