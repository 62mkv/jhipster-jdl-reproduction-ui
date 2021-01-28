import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReproductionUiSharedModule } from 'app/shared/shared.module';
import { RandomNewEntityComponent } from './random-new-entity.component';
import { RandomNewEntityDetailComponent } from './random-new-entity-detail.component';
import { RandomNewEntityUpdateComponent } from './random-new-entity-update.component';
import { RandomNewEntityDeleteDialogComponent } from './random-new-entity-delete-dialog.component';
import { randomNewEntityRoute } from './random-new-entity.route';

@NgModule({
  imports: [ReproductionUiSharedModule, RouterModule.forChild(randomNewEntityRoute)],
  declarations: [
    RandomNewEntityComponent,
    RandomNewEntityDetailComponent,
    RandomNewEntityUpdateComponent,
    RandomNewEntityDeleteDialogComponent,
  ],
  entryComponents: [RandomNewEntityDeleteDialogComponent],
})
export class ReproductionUiRandomNewEntityModule {}
