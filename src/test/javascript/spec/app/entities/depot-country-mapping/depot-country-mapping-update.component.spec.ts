import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ReproductionUiTestModule } from '../../../test.module';
import { DepotCountryMappingUpdateComponent } from 'app/entities/depot-country-mapping/depot-country-mapping-update.component';
import { DepotCountryMappingService } from 'app/entities/depot-country-mapping/depot-country-mapping.service';
import { DepotCountryMapping } from 'app/shared/model/depot-country-mapping.model';

describe('Component Tests', () => {
  describe('DepotCountryMapping Management Update Component', () => {
    let comp: DepotCountryMappingUpdateComponent;
    let fixture: ComponentFixture<DepotCountryMappingUpdateComponent>;
    let service: DepotCountryMappingService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReproductionUiTestModule],
        declarations: [DepotCountryMappingUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DepotCountryMappingUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DepotCountryMappingUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DepotCountryMappingService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DepotCountryMapping(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new DepotCountryMapping();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
