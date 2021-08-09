import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {DemoUserService} from "../../services/demo-user.service";
import {DemoUser} from "../../models/user.model";

@Component({
  selector: 'app-user-details',
  templateUrl: './demo-user-details.component.html',
  styleUrls: ['./demo-user-details.component.css']
})
export class DemoUserDetailsComponent {

  currentUser: DemoUser = new DemoUser();

  constructor(private userService: DemoUserService, private route: ActivatedRoute, private router: Router) {
  }

  deleteUser(): void {
    this.userService.delete(this.currentUser.firstname)
      .subscribe(
        response => {
          console.log(response);
          this.router.navigate(['/user']);
        },
        error => {
          console.log(error);
        });
  }


}
