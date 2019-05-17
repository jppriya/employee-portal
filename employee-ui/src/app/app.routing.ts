import { Routes, RouterModule } from '@angular/router';
import { EmployeesComponent } from '../components/employees/employees.component';
import { employeeRoutes } from '../components/employees/employee.routes';
export const routes: Routes = [{
    path: '',
    pathMatch: 'full',
    redirectTo: 'employee/manage'
}]

export const appRoutes = RouterModule.forRoot(routes);