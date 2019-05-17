import { Validators, FormControl, FormGroup, FormBuilder } from '@angular/forms';

export class EmployeeForm {
    formGroup: FormGroup;
    constructor(private fb: FormBuilder) {
        this.formGroup = this.fb.group({
            'employeeId': new FormControl('', [Validators.required]),
            'firstName': new FormControl('', [Validators.required]),
            'lastName': new FormControl('', [Validators.required]),
            'gender': new FormControl('', Validators.required),
            'dob': new FormControl(''),
        });
    }

    public employeeForm(): FormGroup {
        return this.formGroup;
    }
}