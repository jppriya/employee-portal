import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employees.service';
import { FormBuilder } from '@angular/forms';
import { EmployeeForm } from './employee-form';
import { Employee } from '../employee.model';
import { ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.less'],
  encapsulation: ViewEncapsulation.None

})
export class AddEmployeeComponent implements OnInit {

  employees: any[] = [];
  employeeForm: FormGroup;
  name: string;
  isAdd: boolean = false;
  id: any;
  constructor(
    private router: Router,
    private cdr: ChangeDetectorRef,
    private activatedRoute: ActivatedRoute,
    private employeeService: EmployeeService,
    private fb: FormBuilder
  ) {
    this.employeeForm = new EmployeeForm(this.fb).employeeForm();
    this.name = this.activatedRoute.snapshot.queryParams['firstName'];
    this.id = this.activatedRoute.snapshot.queryParams['employeeId'];
    this.isAdd = this.activatedRoute.snapshot.params['name'] === 'add';
    this.successMessage = undefined;
    if (this.name !== 'add' && !!this.id && this.name) {
      let employee: any = this.activatedRoute.snapshot.data['employee'];
      employee.dob = new Date(employee.dob).toLocaleDateString();
      this.employeeForm.patchValue(employee);
    }
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.cdr.detectChanges();
  }

  manageEmployee() {

    this.router.navigate(['/employee/manage']);
  }

  successMessage: string;
  addEmployee() {
    this.successMessage = undefined;
    let employee: Employee = this.employeeForm.getRawValue() as Employee;
    employee.dob = new Date(employee.dob);
    this.employeeService.addEmployee(employee).subscribe(resp => {
      this.successMessage = 'Employee Added Succesfully!!';
      this.router.navigate(['employee/manage'])
    });
  }

  updateEmployee() {
    this.successMessage = undefined;
    let employee: Employee = this.employeeForm.getRawValue() as Employee;
    employee.dob = new Date(employee.dob);
    this.employeeService.updateEmployee(employee).subscribe(resp => {
      this.successMessage = 'Employee Updated Succesfully!!';
      this.router.navigate(['employee/manage'])
    });
  }
}
