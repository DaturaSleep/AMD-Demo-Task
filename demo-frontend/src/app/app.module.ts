import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AddDemoUserComponent} from './components/add-user/add-demo-user.component';
import {DemoUserListComponent} from './components/user-list/demo-user-list.component';
import {DemoUserDetailsComponent} from './components/user-details/demo-user-details.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { ComputeComponent } from './components/compute-component/compute.component';

@NgModule({
  declarations: [
    AppComponent,
    AddDemoUserComponent,
    DemoUserListComponent,
    DemoUserDetailsComponent,
    ComputeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
