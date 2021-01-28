import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDepotCountryMapping, DepotCountryMapping } from 'app/shared/model/depot-country-mapping.model';
import { DepotCountryMappingService } from './depot-country-mapping.service';
import { DepotCountryMappingComponent } from './depot-country-mapping.component';
import { DepotCountryMappingDetailComponent } from './depot-country-mapping-detail.component';
import { DepotCountryMappingUpdateComponent } from './depot-country-mapping-update.component';

@Injectable({ providedIn: 'root' })
export class DepotCountryMappingResolve implements Resolve<IDepotCountryMapping> {
  constructor(private service: DepotCountryMappingService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDepotCountryMapping> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((depotCountryMapping: HttpResponse<DepotCountryMapping>) => {
          if (depotCountryMapping.body) {
            return of(depotCountryMapping.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DepotCountryMapping());
  }
}

export const depotCountryMappingRoute: Routes = [
  {
    path: '',
    component: DepotCountryMappingComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'reproductionUiApp.depotCountryMapping.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DepotCountryMappingDetailComponent,
    resolve: {
      depotCountryMapping: DepotCountryMappingResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'reproductionUiApp.depotCountryMapping.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DepotCountryMappingUpdateComponent,
    resolve: {
      depotCountryMapping: DepotCountryMappingResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'reproductionUiApp.depotCountryMapping.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DepotCountryMappingUpdateComponent,
    resolve: {
      depotCountryMapping: DepotCountryMappingResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'reproductionUiApp.depotCountryMapping.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
