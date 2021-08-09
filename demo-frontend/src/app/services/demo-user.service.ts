import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DemoUser} from '../models/user.model';

const saveDemoUserUrl = 'http://localhost:8080/demo-user/save';
const deleteDemoUserByUsernameUrl = 'http://localhost:8080/demo-user/delete?username=';
const getAllDemoUsersUrl = 'http://localhost:8080/demo-user/all';
@Injectable({
  providedIn: 'root'
})
export class DemoUserService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<DemoUser[]> {
    return this.http.get<DemoUser[]>(getAllDemoUsersUrl);
  }

  create(data: DemoUser): Observable<any> {
    return this.http.post(saveDemoUserUrl, data);
  }

  delete(username: any): Observable<any> {
    return this.http.delete(`${deleteDemoUserByUsernameUrl}${username}`);
  }

}
