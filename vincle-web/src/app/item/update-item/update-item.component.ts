import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemServiceService } from '../service/item-service.service';
import { CreateItem, Item, UpdateItem } from '../../core/model/item.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-item',
  templateUrl: './update-item.component.html',
  styleUrls: ['./update-item.component.scss']
})
export class UpdateItemComponent implements OnInit {

  private item: Item = {} as any;

  public states: any[] = [
    {value: 'CREATED', viewValue: 'Create'},
    {value: 'WAITING', viewValue: 'Waiting'}
  ];

  public containerTypes: any[] = [
    {value: 'PLASTIC', viewValue: 'Plastic'},
    {value: 'GLASS', viewValue: 'Glass'},

  ];

  public updateForm: FormGroup = new FormGroup({});

  constructor(private readonly route: ActivatedRoute,private readonly itemServiceService: ItemServiceService, private readonly router: Router) {
  }

  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      this.item = JSON.parse(params["item"]);
      if(this.item) {
        this.updateForm = new FormGroup({
          name: new FormControl(this.item.name,[Validators.required]),
          state: new FormControl(this.item.state, [Validators.required]),
          needFridge: new FormControl(this.item.needFridge? '1' : '0', [Validators.required]),
          containerType: new FormControl(this.item.containerType, [Validators.required]),
          weight: new FormControl(this.item.weight, [Validators.required]),
        });
      }
    });


  }

  onFormSubmit() {
    if (this.updateForm.valid) {


      this.itemServiceService.updateItem(this.item.code,{name: this.updateForm.controls['name'].value as string,
        state: this.updateForm.controls['state'].value as string,
        needFridge: this.updateForm.controls['needFridge'].value === '1' ? true : false,
        containerType: this.updateForm.controls['containerType'].value as string,
        weight: this.updateForm.controls['weight'].value as string,
      }).subscribe(() => {
        this.router.navigate(['/list-item']);
      })
    }
  }

}
