import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ReproductionUiTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { DepotCountryMappingDeleteDialogComponent } from 'app/entities/depot-country-mapping/depot-country-mapping-delete-dialog.component';
import { DepotCountryMappingService } from 'app/entities/depot-country-mapping/depot-country-mapping.service';

describe('Component Tests', () => {
  describe('DepotCountryMapping Management Delete Component', () => {
    let comp: DepotCountryMappingDeleteDialogComponent;
    let fixture: ComponentFixture<DepotCountryMappingDeleteDialogComponent>;
    let service: DepotCountryMappingService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ReproductionUiTestModule],
        declarations: [DepotCountryMappingDeleteDialogComponent],
      })
        .overrideTemplate(DepotCountryMappingDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DepotCountryMappingDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DepotCountryMappingService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
