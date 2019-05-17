export interface Employee {
    employeeId: number;
    firstName: string;
    lastName: string;
    gender: string;
    dob: Date;
}

export interface EmployeeWrapper {
    fileBody: string;
    fileText: string;
    fileType: string;
    fileSize: string;
}