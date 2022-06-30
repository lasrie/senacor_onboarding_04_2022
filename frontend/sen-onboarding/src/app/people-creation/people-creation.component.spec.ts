import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PeopleCreationComponent } from './people-creation.component';

describe('PeopleCreationComponent', () => {
  let component: PeopleCreationComponent;
  let fixture: ComponentFixture<PeopleCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PeopleCreationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PeopleCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
