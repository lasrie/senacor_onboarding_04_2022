import { Component, Input, OnInit } from '@angular/core';
import { Person } from '../person';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { PeopleService } from '../people.service';

@Component({
  selector: 'app-people-detail',
  templateUrl: './people-detail.component.html',
  styleUrls: ['./people-detail.component.css']
})
export class PeopleDetailComponent implements OnInit {

  @Input() person?: Person;

  constructor(
    private route: ActivatedRoute,
    private peopleService: PeopleService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getPerson();
  }

  getPerson(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.peopleService.getPerson(id).subscribe(person => this.person = person);
  }

  goBack(): void {
    this.location.back();
  }

}
