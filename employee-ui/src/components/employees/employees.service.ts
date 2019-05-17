import { Injectable } from "@angular/core";
import { Employee } from './employee.model';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class EmployeeService {

    private baseUrl: string = 'http://localhost:8080/';

    constructor(private httpClient: HttpClient) {

    }

    getAllEmployee() {
        return this.httpClient.get(this.baseUrl + 'employee');
    }

    getEmployeeById(empId: any) {
        return this.httpClient.get(this.baseUrl + 'employee/' + empId);
    }

    addEmployee(employee: Employee): any {
        return this.httpClient.post(this.baseUrl + 'employee', employee);
    }
    updateEmployee(employee: Employee): any {
        return this.httpClient.put(this.baseUrl + 'employee/' + employee.employeeId, employee);
    }


}