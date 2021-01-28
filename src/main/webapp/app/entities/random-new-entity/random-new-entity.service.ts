import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRandomNewEntity } from 'app/shared/model/random-new-entity.model';

type EntityResponseType = HttpResponse<IRandomNewEntity>;
type EntityArrayResponseType = HttpResponse<IRandomNewEntity[]>;

@Injectable({ providedIn: 'root' })
export class RandomNewEntityService {
  public resourceUrl = SERVER_API_URL + 'api/random-new-entities';

  constructor(protected http: HttpClient) {}

  create(randomNewEntity: IRandomNewEntity): Observable<EntityResponseType> {
    return this.http.post<IRandomNewEntity>(this.resourceUrl, randomNewEntity, { observe: 'response' });
  }

  update(randomNewEntity: IRandomNewEntity): Observable<EntityResponseType> {
    return this.http.put<IRandomNewEntity>(this.resourceUrl, randomNewEntity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRandomNewEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRandomNewEntity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
