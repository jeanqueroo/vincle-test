import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateItem, Item, UpdateItem } from '../../core/model/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemServiceService {

  private url = 'http://localhost:8080/api/v1/item'

  constructor(private readonly http: HttpClient) { }

  getItem(code: string, itemType : string): Observable<Item[]> {
    return this.http.get<Item[]>(this.url + '?code=' + code + '&itemType=' + itemType,{});
  }

  deleteItem(code: string): Observable<Item> {
    return this.http.delete<Item>(this.url + '/' + code ,{});
  }

  createItem(item: CreateItem): Observable<void> {
    return this.http.post<void>(this.url, item);
  }

  updateItem(code: string, item: UpdateItem): Observable<Item> {
    return this.http.put<Item>(this.url + '/' + code ,item);
  }


}
