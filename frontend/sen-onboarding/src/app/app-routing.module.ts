import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GroupListComponent } from './group-list/group-list.component';
import { PeopleDetailComponent } from './people-detail/people-detail.component';
import { PeopleListComponent } from './people-list/people-list.component';

const routes: Routes = [
  {path: 'people', component: PeopleListComponent},
  {path: 'groups', component: GroupListComponent},
  { path: 'detail/:id', component: PeopleDetailComponent },
  { path: '', redirectTo: '/people', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
