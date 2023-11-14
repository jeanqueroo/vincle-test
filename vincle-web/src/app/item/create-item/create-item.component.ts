import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ItemServiceService } from '../service/item-service.service';
import { CreateItem, Item } from '../../core/model/item.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.scss']
})
export class CreateItemComponent {

  public itemTypes: any[] = [
    {value: 'DRINK', viewValue: 'Drink'},
    {value: 'FOOD', viewValue: 'Food'},
    {value: 'SAUCE', viewValue: 'Sauce'},
    {value: 'SPICE', viewValue: 'Spicw'},
  ];

  public containerTypes: any[] = [
    {value: 'PLASTIC', viewValue: 'Plastic'},
    {value: 'GLASS', viewValue: 'Glass'},

  ];

  public createForm = new FormGroup({
    name: new FormControl('',[Validators.required]),
    itemType: new FormControl('', [Validators.required]),
    needFridge: new FormControl('', [Validators.required]),
    containerType: new FormControl('', [Validators.required]),
    weight: new FormControl('', [Validators.required]),
  });




  constructor(private readonly itemServiceService: ItemServiceService, private readonly router: Router) {}


  onFormSubmit() {
    if (this.createForm.valid) {
        const item: CreateItem = {name: this.createForm.controls.name.value as string,
          itemType: this.createForm.controls.itemType.value as string,
          needFridge: this.createForm.controls.needFridge.value === '1' ? true : false,
          containerType: this.createForm.controls.containerType.value as string,
          weight: this.createForm.controls.weight.value as string,
        } ;

      this.itemServiceService.createItem(item).subscribe(() => {
        this.router.navigate(['/list-item']);
      })
    }
  }
}
