import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ReproductionUiTestModule } from '../../../test.module';
import { BusinessUnitConfigUpdateComponent } from 'app/entities/business-unit-config/business-unit-config-update.component';
import { BusinessUnitConfigService } from 'app/entities/business-unit-config/business-unit-config.service';
import { BusinessUnitConfig } from 'app/shared/model/business-unit-config.model';

describe('Component Tests', () => {
  describe('BusinessUnitConfig Management Update Component', () => {
    let comp: BusinessUnitConfigUpdateComponent;
    let fixture: ComponentFixture<BusinessUnitConfigUpdateComponent>;
    let service: BusinessUnitConfigService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReproductionUiTestModule],
        declarations: [BusinessUnitConfigUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BusinessUnitConfigUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BusinessUnitConfigUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BusinessUnitConfigService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BusinessUnitConfig(123);
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
        const entity = new BusinessUnitConfig();
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
