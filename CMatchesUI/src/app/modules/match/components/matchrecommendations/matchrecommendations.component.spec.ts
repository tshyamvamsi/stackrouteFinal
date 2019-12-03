import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchrecommendationsComponent } from './matchrecommendations.component';

xdescribe('MatchrecommendationsComponent', () => {
  let component: MatchrecommendationsComponent;
  let fixture: ComponentFixture<MatchrecommendationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MatchrecommendationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchrecommendationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
