import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBusinessUnitConfig } from 'app/shared/model/business-unit-config.model';

type EntityResponseType = HttpResponse<IBusinessUnitConfig>;
type EntityArrayResponseType = HttpResponse<IBusinessUnitConfig[]>;

@Injectable({ providedIn: 'root' })
export class BusinessUnitConfigService {
  public resourceUrl = SERVER_API_URL + 'api/business-unit-configs';

  constructor(protected http: HttpClient) {}

  create(businessUnitConfig: IBusinessUnitConfig): Observable<EntityResponseType> {
    return this.http.post<IBusinessUnitConfig>(this.resourceUrl, businessUnitConfig, { observe: 'response' });
  }

  update(businessUnitConfig: IBusinessUnitConfig): Observable<EntityResponseType> {
    return this.http.put<IBusinessUnitConfig>(this.resourceUrl, businessUnitConfig, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBusinessUnitConfig>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBusinessUnitConfig[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
