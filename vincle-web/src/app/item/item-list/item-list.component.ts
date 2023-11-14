import { Component, OnInit } from '@angular/core';
import { ItemServiceService } from '../service/item-service.service';
import { Item } from '../../core/model/item.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit  {

  public items: Item[]= [];

  public itemTypes: any[] = [
    {value: 'DRINK', viewValue: 'Drink'},
    {value: 'FOOD', viewValue: 'Food'},
    {value: 'SAUCE', viewValue: 'Sauce'},
    {value: 'SPICE', viewValue: 'Spicw'},
  ];

  public searchForm = new FormGroup({
    code: new FormControl('',[Validators.required]),
    itemType: new FormControl('', [Validators.required])
  });

  displayedColumns: string[] = ['code', 'name', 'weight', 'state', 'action'];

  constructor(private readonly itemServiceService: ItemServiceService,private readonly router: Router) {}

  ngOnInit(): void {

  }

  onFormSubmit() {
    if (this.searchForm.valid) {
      this.itemServiceService.getItem(this.searchForm.controls.code.value?.toUpperCase() as string,this.searchForm.controls.itemType.value as string).subscribe(item => {
        this.items = item;
      });
    }
  }

  deleteItem(item: Item) {
    this.itemServiceService.deleteItem(item.code).subscribe(value => {
      this.itemServiceService.getItem(this.searchForm.controls.code.value?.toUpperCase() as string,this.searchForm.controls.itemType.value as string).subscribe(item => {
        this.items = item;
      });
    });
  }

  updateItem(item: Item) {
    const navigationExtras: NavigationExtras = {
      queryParams: {
        "item": JSON.stringify(item)
      }
    };
    this.router.navigate(['/update-item'], navigationExtras);
  }
}
