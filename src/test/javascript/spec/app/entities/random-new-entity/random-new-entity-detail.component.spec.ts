import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ReproductionUiTestModule } from '../../../test.module';
import { RandomNewEntityDetailComponent } from 'app/entities/random-new-entity/random-new-entity-detail.component';
import { RandomNewEntity } from 'app/shared/model/random-new-entity.model';

describe('Component Tests', () => {
  describe('RandomNewEntity Management Detail Component', () => {
    let comp: RandomNewEntityDetailComponent;
    let fixture: ComponentFixture<RandomNewEntityDetailComponent>;
    const route = ({ data: of({ randomNewEntity: new RandomNewEntity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReproductionUiTestModule],
        declarations: [RandomNewEntityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RandomNewEntityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RandomNewEntityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load randomNewEntity on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.randomNewEntity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
