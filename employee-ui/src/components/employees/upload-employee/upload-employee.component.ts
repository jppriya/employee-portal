import { EmployeeService } from './../employees.service';
import { Component, OnInit } from '@angular/core';
import { FileState, SelectEvent, RemoveEvent, FileRestrictions } from '@progress/kendo-angular-upload';
import { FormGroup } from '@angular/forms';
import { Input } from '@angular/core';
import { Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { EmployeeWrapper } from '../employee.model';

@Component({
  selector: 'app-upload-employee',
  templateUrl: './upload-employee.component.html',
  styleUrls: ['./upload-employee.component.less']
})
export class UploadEmployeeComponent implements OnInit {
  uploadSaveUrl = 'saveUrl'; // should represent an actual API endpoint
  uploadRemoveUrl = 'removeUrl'; // should represent an actual API endpoint
  edi: FormGroup;
  showSuccessMessage: boolean;
  constructor(private fb: FormBuilder,
    private employeeService: EmployeeService,
    private httpClient: HttpClient) {
    this.edi = this.fb.group({
      files: new FormControl()
    });

  }
  uploadRestrictions: FileRestrictions = {
    allowedExtensions: ['.txt', '.json', '.xlsx']
  };

  ngOnInit() {
  }

  uploadFiles() {
    let formVallue = this.edi.get('files').value[0];
    this.uploadBulkEmployeeFiles(formVallue.rawFile);
  }

  uploadBulkEmployeeFiles(files: File) {
    let fileReader = new FileReader();
    let fileObj: EmployeeWrapper = {} as EmployeeWrapper;
    fileReader.readAsArrayBuffer(files);
    let fileType: string;
    if (files.type) {
      let fileArray: string[] = files.name.split('.');
      fileType = fileArray[fileArray.length - 1];
    }
    this.delay(300, fileObj, fileReader, files);
  }

  delay(ms: number, fileObj: EmployeeWrapper, fileReader: FileReader, files: File) {
    setTimeout(() => {
      this.showSuccessMessage = false;
      fileObj.fileBody = this.arrayBufferToBase64(fileReader.result);
      this.httpClient.post('http://localhost:8080/upload', fileObj).subscribe((response: any) => {
        this.showSuccessMessage = true;
      }, (err) => {
        this.showSuccessMessage = false;
      });
    }, ms);
  }

  arrayBufferToBase64(buffer) {
    let binary = '';
    let bytes = new Uint8Array(buffer);
    let len = bytes.byteLength;
    for (let i = 0; i < len; i++) {
      binary += String.fromCharCode(bytes[i]);
    }
    return window.btoa(binary);
  }

}
