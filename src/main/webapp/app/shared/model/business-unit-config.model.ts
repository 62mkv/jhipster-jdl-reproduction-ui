export interface IBusinessUnitConfig {
  id?: number;
  unitName?: string;
  defaultLabelQueue?: string;
}

export class BusinessUnitConfig implements IBusinessUnitConfig {
  constructor(public id?: number, public unitName?: string, public defaultLabelQueue?: string) {}
}
