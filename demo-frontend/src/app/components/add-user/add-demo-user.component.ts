import {Component, OnInit} from '@angular/core';
import {DemoUser} from "../../models/user.model";
import {DemoUserService} from "../../services/demo-user.service";

@Component({
  selector: 'app-add-demo-user',
  templateUrl: './add-demo-user.component.html',
  styleUrls: ['./add-demo-user.component.css']
})
export class AddDemoUserComponent implements OnInit {

  user: DemoUser;

  submitted = false;

  constructor(private userService: DemoUserService) {
    this.user = new DemoUser();
  }

  saveUser(): void {
    this.userService.create(this.user)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  newUser(): void {
    this.submitted = false;
    this.user = new DemoUser();
  }

  ngOnInit(): void {
  }

}
