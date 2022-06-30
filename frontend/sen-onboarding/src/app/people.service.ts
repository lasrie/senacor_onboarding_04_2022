import { Injectable } from '@angular/core';
import { PERSONS } from './mock-persons';
import { Person } from './person';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  constructor(private messageService: MessageService) { }

  getPeople(): Observable<Person[]>{
    const people = of(PERSONS);
    this.messageService.add('PeopleService: fetched People');
    return people;
  }

  getPerson(id: number): Observable<Person>{
    const person = PERSONS.find(p => p.id === id)!;
    this.messageService.add(`PeopleService: fetched hero id=${id}`);
    return of(person);
  }
}
