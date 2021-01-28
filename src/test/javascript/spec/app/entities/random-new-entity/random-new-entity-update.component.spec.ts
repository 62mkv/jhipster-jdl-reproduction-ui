import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ReproductionUiTestModule } from '../../../test.module';
import { RandomNewEntityUpdateComponent } from 'app/entities/random-new-entity/random-new-entity-update.component';
import { RandomNewEntityService } from 'app/entities/random-new-entity/random-new-entity.service';
import { RandomNewEntity } from 'app/shared/model/random-new-entity.model';

describe('Component Tests', () => {
  describe('RandomNewEntity Management Update Component', () => {
    let comp: RandomNewEntityUpdateComponent;
    let fixture: ComponentFixture<RandomNewEntityUpdateComponent>;
    let service: RandomNewEntityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReproductionUiTestModule],
        declarations: [RandomNewEntityUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RandomNewEntityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RandomNewEntityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RandomNewEntityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RandomNewEntity(123);
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
        const entity = new RandomNewEntity();
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
