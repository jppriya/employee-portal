import { employeeRoutes } from './employee.routes';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddEmployeeComponent, ManageEmployeeComponent, UploadEmployeeComponent, EmployeesComponent } from './index';
import { EmployeeService } from './employees.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EmployeeResolver, EditEmployeeResolver, UploadInterceptor } from './employee.resolver';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { UploadModule } from '@progress/kendo-angular-upload';

@NgModule({
  imports: [
    CommonModule,
    employeeRoutes,
    FormsModule,
    HttpClientModule,
    UploadModule,
    ReactiveFormsModule
  ],
  declarations: [
    EmployeesComponent,
    AddEmployeeComponent,
    ManageEmployeeComponent,
    UploadEmployeeComponent,

  ],
  providers: [EmployeeService, EmployeeResolver, EditEmployeeResolver,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: UploadInterceptor,
      multi: true
    }]
})
export class EmployeeModule { }
