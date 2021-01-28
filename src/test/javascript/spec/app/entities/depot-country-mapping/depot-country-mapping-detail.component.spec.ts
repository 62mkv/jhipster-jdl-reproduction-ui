import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReproductionUiTestModule } from '../../../test.module';
import { DepotCountryMappingDetailComponent } from 'app/entities/depot-country-mapping/depot-country-mapping-detail.component';
import { DepotCountryMapping } from 'app/shared/model/depot-country-mapping.model';

describe('Component Tests', () => {
  describe('DepotCountryMapping Management Detail Component', () => {
    let comp: DepotCountryMappingDetailComponent;
    let fixture: ComponentFixture<DepotCountryMappingDetailComponent>;
    const route = ({ data: of({ depotCountryMapping: new DepotCountryMapping(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReproductionUiTestModule],
        declarations: [DepotCountryMappingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DepotCountryMappingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DepotCountryMappingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load depotCountryMapping on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.depotCountryMapping).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
