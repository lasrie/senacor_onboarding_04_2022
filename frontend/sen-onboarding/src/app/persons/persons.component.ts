import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { FormsModule } from '@angular/forms'; 

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {

  constructor() { }

  hero: Person = {
    firstName: 'Windstorm',
    lastName: 'Hero',
    age: 743,
    id: 2
  }

  ngOnInit(): void {
  }

}
