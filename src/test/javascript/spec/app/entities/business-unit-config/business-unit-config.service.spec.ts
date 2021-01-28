import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BusinessUnitConfigService } from 'app/entities/business-unit-config/business-unit-config.service';
import { IBusinessUnitConfig, BusinessUnitConfig } from 'app/shared/model/business-unit-config.model';

describe('Service Tests', () => {
  describe('BusinessUnitConfig Service', () => {
    let injector: TestBed;
    let service: BusinessUnitConfigService;
    let httpMock: HttpTestingController;
    let elemDefault: IBusinessUnitConfig;
    let expectedResult: IBusinessUnitConfig | IBusinessUnitConfig[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BusinessUnitConfigService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new BusinessUnitConfig(0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BusinessUnitConfig', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new BusinessUnitConfig()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BusinessUnitConfig', () => {
        const returnedFromService = Object.assign(
          {
            unitName: 'BBBBBB',
            defaultLabelQueue: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BusinessUnitConfig', () => {
        const returnedFromService = Object.assign(
          {
            unitName: 'BBBBBB',
            defaultLabelQueue: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BusinessUnitConfig', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
