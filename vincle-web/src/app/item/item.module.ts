import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemListComponent } from './item-list/item-list.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatListModule } from '@angular/material/list';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatPaginatorModule } from '@angular/material/paginator';
import { RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { MatSelectModule } from '@angular/material/select';
import { CreateItemComponent } from './create-item/create-item.component';
import { MatRadioModule } from '@angular/material/radio';
import { UpdateItemComponent } from './update-item/update-item.component';




@NgModule({
  declarations: [
    ItemListComponent,MenuComponent, CreateItemComponent, UpdateItemComponent
  ],
  imports: [
    CommonModule, MatToolbarModule, MatIconModule, MatButtonModule, MatMenuModule, BrowserAnimationsModule,
    MatListModule,HttpClientModule, MatCardModule, MatTableModule, MatDialogModule, MatInputModule,
    MatFormFieldModule, FormsModule, ReactiveFormsModule, MatSnackBarModule, MatPaginatorModule,
    MatSelectModule,MatRadioModule,
    RouterModule.forRoot([
      {path: 'create-item', component: CreateItemComponent},
      {path: 'list-item', component: ItemListComponent},
      {path: 'update-item', component: UpdateItemComponent},
      {path: '', redirectTo: '/list-item', pathMatch: 'full'}
    ]),
  ]
})
export class ItemModule { }
