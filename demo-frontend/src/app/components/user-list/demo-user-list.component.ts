import {Component, OnInit} from '@angular/core';
import {DemoUserService} from "../../services/demo-user.service";
import {DemoUser} from "../../models/user.model";

@Component({
  selector: 'app-user-list',
  templateUrl: './demo-user-list.component.html',
  styleUrls: ['./demo-user-list.component.css']
})
export class DemoUserListComponent implements OnInit {

  users?: DemoUser[];
  currentUser?: DemoUser;
  currentIndex = -1;
  userName = '';

  constructor(private userService: DemoUserService) {
  }

  ngOnInit(): void {
    this.retrieveUsers();
  }

  retrieveUsers(): void {
    this.userService.getAll()
      .subscribe(
        data => {
          this.users = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  deleteUserByUsername(): void {
    this.userService.delete(this.currentUser?.username)
      .subscribe(
        data => {
          this.retrieveUsers();
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  setActiveUser(user: DemoUser, index: number): void {
    this.currentUser = user;
    this.currentIndex = index;
  }


}

