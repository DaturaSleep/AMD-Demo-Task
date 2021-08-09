import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoUserListComponent } from './demo-user-list.component';

describe('DemoUserListComponent', () => {
  let component: DemoUserListComponent;
  let fixture: ComponentFixture<DemoUserListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemoUserListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemoUserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
