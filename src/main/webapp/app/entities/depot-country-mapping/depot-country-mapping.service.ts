import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDepotCountryMapping } from 'app/shared/model/depot-country-mapping.model';

type EntityResponseType = HttpResponse<IDepotCountryMapping>;
type EntityArrayResponseType = HttpResponse<IDepotCountryMapping[]>;

@Injectable({ providedIn: 'root' })
export class DepotCountryMappingService {
  public resourceUrl = SERVER_API_URL + 'api/depot-country-mappings';

  constructor(protected http: HttpClient) {}

  create(depotCountryMapping: IDepotCountryMapping): Observable<EntityResponseType> {
    return this.http.post<IDepotCountryMapping>(this.resourceUrl, depotCountryMapping, { observe: 'response' });
  }

  update(depotCountryMapping: IDepotCountryMapping): Observable<EntityResponseType> {
    return this.http.put<IDepotCountryMapping>(this.resourceUrl, depotCountryMapping, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDepotCountryMapping>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDepotCountryMapping[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
