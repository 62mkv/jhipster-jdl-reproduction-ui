import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRandomNewEntity, RandomNewEntity } from 'app/shared/model/random-new-entity.model';
import { RandomNewEntityService } from './random-new-entity.service';
import { RandomNewEntityComponent } from './random-new-entity.component';
import { RandomNewEntityDetailComponent } from './random-new-entity-detail.component';
import { RandomNewEntityUpdateComponent } from './random-new-entity-update.component';

@Injectable({ providedIn: 'root' })
export class RandomNewEntityResolve implements Resolve<IRandomNewEntity> {
  constructor(private service: RandomNewEntityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRandomNewEntity> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((randomNewEntity: HttpResponse<RandomNewEntity>) => {
          if (randomNewEntity.body) {
            return of(randomNewEntity.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RandomNewEntity());
  }
}

export const randomNewEntityRoute: Routes = [
  {
    path: '',
    component: RandomNewEntityComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'reproductionUiApp.randomNewEntity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RandomNewEntityDetailComponent,
    resolve: {
      randomNewEntity: RandomNewEntityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'reproductionUiApp.randomNewEntity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RandomNewEntityUpdateComponent,
    resolve: {
      randomNewEntity: RandomNewEntityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'reproductionUiApp.randomNewEntity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RandomNewEntityUpdateComponent,
    resolve: {
      randomNewEntity: RandomNewEntityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'reproductionUiApp.randomNewEntity.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
