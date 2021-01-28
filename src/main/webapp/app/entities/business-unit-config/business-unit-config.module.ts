import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReproductionUiSharedModule } from 'app/shared/shared.module';
import { BusinessUnitConfigComponent } from './business-unit-config.component';
import { BusinessUnitConfigDetailComponent } from './business-unit-config-detail.component';
import { BusinessUnitConfigUpdateComponent } from './business-unit-config-update.component';
import { BusinessUnitConfigDeleteDialogComponent } from './business-unit-config-delete-dialog.component';
import { businessUnitConfigRoute } from './business-unit-config.route';

@NgModule({
  imports: [ReproductionUiSharedModule, RouterModule.forChild(businessUnitConfigRoute)],
  declarations: [
    BusinessUnitConfigComponent,
    BusinessUnitConfigDetailComponent,
    BusinessUnitConfigUpdateComponent,
    BusinessUnitConfigDeleteDialogComponent,
  ],
  entryComponents: [BusinessUnitConfigDeleteDialogComponent],
})
export class ReproductionUiBusinessUnitConfigModule {}
