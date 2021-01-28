export interface IRandomNewEntity {
  id?: number;
  entityName?: string;
  someValue?: string;
}

export class RandomNewEntity implements IRandomNewEntity {
  constructor(public id?: number, public entityName?: string, public someValue?: string) {}
}
