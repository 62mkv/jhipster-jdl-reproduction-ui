import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReproductionUiTestModule } from '../../../test.module';
import { BusinessUnitConfigDetailComponent } from 'app/entities/business-unit-config/business-unit-config-detail.component';
import { BusinessUnitConfig } from 'app/shared/model/business-unit-config.model';

describe('Component Tests', () => {
  describe('BusinessUnitConfig Management Detail Component', () => {
    let comp: BusinessUnitConfigDetailComponent;
    let fixture: ComponentFixture<BusinessUnitConfigDetailComponent>;
    const route = ({ data: of({ businessUnitConfig: new BusinessUnitConfig(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReproductionUiTestModule],
        declarations: [BusinessUnitConfigDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BusinessUnitConfigDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BusinessUnitConfigDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load businessUnitConfig on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.businessUnitConfig).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
