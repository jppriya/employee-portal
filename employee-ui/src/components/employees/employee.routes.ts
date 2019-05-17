import { EmployeeResolver, EditEmployeeResolver } from './employee.resolver';
import { Routes, RouterModule } from '@angular/router';
import { AddEmployeeComponent, ManageEmployeeComponent, UploadEmployeeComponent, } from './index';
import { EmployeesComponent } from './employees.component';

export const routes: Routes = [
    {
        path: 'employee',
        component: EmployeesComponent,
        children: [

            {
                path: 'manage',
                component: ManageEmployeeComponent,
                resolve: {
                    employee: EmployeeResolver
                }
            },
            {
                path: 'upload',
                component: UploadEmployeeComponent
            },
            {
                path: ':name',
                component: AddEmployeeComponent,
                resolve: {
                    employee: EditEmployeeResolver
                }
            }
        ]
    }

]

export const employeeRoutes = RouterModule.forRoot(routes);
