import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoUserDetailsComponent } from './demo-user-details.component';

describe('DemoUserDetailsComponent', () => {
  let component: DemoUserDetailsComponent;
  let fixture: ComponentFixture<DemoUserDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemoUserDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemoUserDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
