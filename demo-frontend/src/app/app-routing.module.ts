import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DemoUserListComponent} from './components/user-list/demo-user-list.component';
import {DemoUserDetailsComponent} from './components/user-details/demo-user-details.component';
import {AddDemoUserComponent} from './components/add-user/add-demo-user.component';
import {ComputeComponent} from "./components/compute-component/compute.component";

const routes: Routes = [
  {path: '', redirectTo: 'users', pathMatch: 'full'},
  {path: 'users', component: DemoUserListComponent},
  {path: 'users/:id', component: DemoUserDetailsComponent},
  {path: 'add', component: AddDemoUserComponent},
  {path: 'compute', component: ComputeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
