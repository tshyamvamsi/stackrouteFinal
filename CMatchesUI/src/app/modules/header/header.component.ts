import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from '../authentication/authentication.service';
import { AfterViewChecked } from '@angular/core/src/metadata/lifecycle_hooks';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, AfterViewChecked {

  showLogout : boolean;
  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  ngAfterViewChecked(){
    if(!this.authenticationService.isTokenExpired()){
      this.showLogout = true;
    }
  }

  logout(){
    console.log('logout -->');
    this.showLogout = false;
    this.authenticationService.logout();
    //this.router.navigate(['/login']);
  }

}
