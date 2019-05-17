import { EmployeeService } from './employees.service';
import { Resolve } from "@angular/router";
import { ActivatedRouteSnapshot } from "@angular/router";
import { RouterStateSnapshot } from "@angular/router";
import { Injectable } from '@angular/core';
import { map, delay } from "rxjs/operators";
import { concat } from 'rxjs/observable/concat';

import { Observable } from 'rxjs/Rx';
import { HttpInterceptor, HttpProgressEvent } from '@angular/common/http';
import { HttpRequest } from '@angular/common/http';
import { HttpHandler } from '@angular/common/http';
import { HttpEvent } from '@angular/common/http';
import { of } from 'rxjs';
import { HttpEventType } from '@angular/common/http';
import { HttpResponse } from '@angular/common/http';


@Injectable()
export class EmployeeResolver implements Resolve<any> {
    constructor(private employeeService: EmployeeService) {

    }
    resolve(route: ActivatedRouteSnapshot) {
        return this.employeeService.getAllEmployee().pipe(map(response => response));
    }
}

@Injectable()
export class EditEmployeeResolver implements Resolve<any> {
    private name: string;
    private id: string;
    private isAdd: boolean = false;
    constructor(private productsService: EmployeeService) {

    }
    resolve(route: ActivatedRouteSnapshot) {
        this.id = route.queryParams['employeeId'];
        this.isAdd = route.params['name'] === 'add';

        if (this.name !== 'add' && !!this.id) {
            return this.productsService.getEmployeeById(this.id).map(data => data);
        }
        return Observable.of(null);
    }
}


@Injectable()
export class UploadInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.url === 'saveUrl') {
            const events: Observable<HttpEvent<any>>[] = [0, 30, 60, 100].map((x) => of(<HttpProgressEvent>{
                type: HttpEventType.UploadProgress,
                loaded: x,
                total: 100
            }).pipe(delay(1000)));

            const success = of(new HttpResponse({ status: 200, statusText: "Saved Successfuly" })).pipe(delay(1000));
            events.push(success);

            return concat(...events);
        }

        if (req.url === 'removeUrl') {
            return of(new HttpResponse({ status: 200 }));
        }

        return next.handle(req);
    }
}