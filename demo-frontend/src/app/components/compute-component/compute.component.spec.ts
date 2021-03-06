import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComputeComponent } from './compute.component';

describe('ComputeComponentComponent', () => {
  let component: ComputeComponent;
  let fixture: ComponentFixture<ComputeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComputeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComputeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
