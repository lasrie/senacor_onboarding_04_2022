import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';
import { PERSONS } from '../mock-persons';
import { PeopleService } from '../people.service';
import { Person } from '../person';
@Component({
  selector: 'app-people-list',
  templateUrl: './people-list.component.html',
  styleUrls: ['./people-list.component.css']
})
export class PeopleListComponent implements OnInit {

  people: Person[] = [];
  constructor(private peopleService: PeopleService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.getPeople();
  }


  getPeople(): void{
    this.peopleService.getPeople().subscribe(people =>{
      this.people = people;
    })
  }
}
