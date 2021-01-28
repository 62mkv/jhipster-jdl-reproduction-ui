export interface IDepotCountryMapping {
  id?: number;
  depotName?: string;
  countryCode?: string;
}

export class DepotCountryMapping implements IDepotCountryMapping {
  constructor(public id?: number, public depotName?: string, public countryCode?: string) {}
}
