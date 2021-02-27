import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubControlComponent } from './sub-control.component';

describe('SubControlComponent', () => {
  let component: SubControlComponent;
  let fixture: ComponentFixture<SubControlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubControlComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
