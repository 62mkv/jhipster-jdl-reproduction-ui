import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'depot-country-mapping',
        loadChildren: () =>
          import('./depot-country-mapping/depot-country-mapping.module').then(m => m.ReproductionUiDepotCountryMappingModule),
      },
      {
        path: 'business-unit-config',
        loadChildren: () =>
          import('./business-unit-config/business-unit-config.module').then(m => m.ReproductionUiBusinessUnitConfigModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ReproductionUiEntityModule {}
