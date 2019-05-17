import { Employee } from './../employee.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employees.service';
import { ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-manage-employee',
  templateUrl: './manage-employee.component.html',
  styleUrls: ['./manage-employee.component.less'],
  encapsulation: ViewEncapsulation.None

})
export class ManageEmployeeComponent implements OnInit {
  employees: any = [];
  successMessage: string;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private employeeService: EmployeeService
  ) {
    this.employees = this.activatedRoute.snapshot.data['employee'];
    debugger
  }


  private getProducts() {
    this.employees = [];
    this.employeeService.getAllEmployee().subscribe(val => {
      this.employees = val;
    }, (err) => { });
  }

  ngOnInit() {

  }

  editEmployeeDetails(employee: Employee) {
    this.successMessage = undefined;
    this.router.navigate([`/employee/edit`], {
      queryParams: {
        'employeeId': employee.employeeId,
        'firstName': employee.firstName,
        'lastName': employee.lastName
      }
    });
  }

  getDOB(employee: Employee) {
    return new Date(employee.dob).toLocaleDateString();
  }

  addEmployee() {
    this.successMessage = undefined;
    this.router.navigate([`/employee/add`]);
  }
  upload() {
    this.successMessage = undefined;
    this.router.navigate([`/employee/upload`]);
  }


}
