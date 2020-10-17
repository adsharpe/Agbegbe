import { Component, OnInit } from '@angular/core';
import { NgModel } from '@angular/forms';
import { DropdownItem } from '../../../class/dropdown-item';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  serviceType : DropdownItem[] = [
    { id: 0, name: 'Full Service' },
    { id: 1, name: 'Burial' },
    { id: 2, name: 'Cremation' },
  ];

  sortBy : DropdownItem[] = [
    { id: 0, name: 'Distance' },
    { id: 1, name: 'Rating' },
    { id: 2, name: 'Price' },
  ];

  doSearch(formQuery : NgModel) {
  }

}
