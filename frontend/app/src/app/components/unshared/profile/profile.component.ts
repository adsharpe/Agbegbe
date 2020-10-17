import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private userService: UserService, private activatedRoute : ActivatedRoute) {
    this.activatedRoute.paramMap.subscribe(
      params => {
        this.username = params.get('username');
        this.category = params.get('category');
    });
  }

  public username : string;
  public category : string;

  ngOnInit(): void {
    console.log('username=', this.username);
    console.log('category=', this.category);
  }

}
