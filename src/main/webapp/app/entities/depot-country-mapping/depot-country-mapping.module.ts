import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ReproductionUiSharedModule } from 'app/shared/shared.module';
import { DepotCountryMappingComponent } from './depot-country-mapping.component';
import { DepotCountryMappingDetailComponent } from './depot-country-mapping-detail.component';
import { DepotCountryMappingUpdateComponent } from './depot-country-mapping-update.component';
import { DepotCountryMappingDeleteDialogComponent } from './depot-country-mapping-delete-dialog.component';
import { depotCountryMappingRoute } from './depot-country-mapping.route';

@NgModule({
  imports: [ReproductionUiSharedModule, RouterModule.forChild(depotCountryMappingRoute)],
  declarations: [
    DepotCountryMappingComponent,
    DepotCountryMappingDetailComponent,
    DepotCountryMappingUpdateComponent,
    DepotCountryMappingDeleteDialogComponent,
  ],
  entryComponents: [DepotCountryMappingDeleteDialogComponent],
})
export class ReproductionUiDepotCountryMappingModule {}
