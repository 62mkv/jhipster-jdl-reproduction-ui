import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { ReproductionUiTestModule } from '../../../test.module';
import { DepotCountryMappingComponent } from 'app/entities/depot-country-mapping/depot-country-mapping.component';
import { DepotCountryMappingService } from 'app/entities/depot-country-mapping/depot-country-mapping.service';
import { DepotCountryMapping } from 'app/shared/model/depot-country-mapping.model';

describe('Component Tests', () => {
  describe('DepotCountryMapping Management Component', () => {
    let comp: DepotCountryMappingComponent;
    let fixture: ComponentFixture<DepotCountryMappingComponent>;
    let service: DepotCountryMappingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReproductionUiTestModule],
        declarations: [DepotCountryMappingComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(DepotCountryMappingComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DepotCountryMappingComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DepotCountryMappingService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DepotCountryMapping(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.depotCountryMappings && comp.depotCountryMappings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DepotCountryMapping(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.depotCountryMappings && comp.depotCountryMappings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
