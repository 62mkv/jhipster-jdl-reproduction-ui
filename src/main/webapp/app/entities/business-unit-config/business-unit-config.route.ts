import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBusinessUnitConfig, BusinessUnitConfig } from 'app/shared/model/business-unit-config.model';
import { BusinessUnitConfigService } from './business-unit-config.service';
import { BusinessUnitConfigComponent } from './business-unit-config.component';
import { BusinessUnitConfigDetailComponent } from './business-unit-config-detail.component';
import { BusinessUnitConfigUpdateComponent } from './business-unit-config-update.component';

@Injectable({ providedIn: 'root' })
export class BusinessUnitConfigResolve implements Resolve<IBusinessUnitConfig> {
  constructor(private service: BusinessUnitConfigService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBusinessUnitConfig> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((businessUnitConfig: HttpResponse<BusinessUnitConfig>) => {
          if (businessUnitConfig.body) {
            return of(businessUnitConfig.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BusinessUnitConfig());
  }
}

export const businessUnitConfigRoute: Routes = [
  {
    path: '',
    component: BusinessUnitConfigComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'reproductionUiApp.businessUnitConfig.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BusinessUnitConfigDetailComponent,
    resolve: {
      businessUnitConfig: BusinessUnitConfigResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'reproductionUiApp.businessUnitConfig.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BusinessUnitConfigUpdateComponent,
    resolve: {
      businessUnitConfig: BusinessUnitConfigResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'reproductionUiApp.businessUnitConfig.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BusinessUnitConfigUpdateComponent,
    resolve: {
      businessUnitConfig: BusinessUnitConfigResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'reproductionUiApp.businessUnitConfig.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
