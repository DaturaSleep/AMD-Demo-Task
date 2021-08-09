import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-compute-component',
  templateUrl: './compute.component.html',
  styleUrls: ['./compute.component.css']
})
export class ComputeComponent implements OnInit {
  firstNumber: number = 0;
  secondNumber: number = 0;
  summedNumber: number = 0;

  constructor() {
  }

  ngOnInit(): void {
  }

  sumTwoNumbers(): void {
    this.summedNumber = this.firstNumber + this.secondNumber;
  }

}
