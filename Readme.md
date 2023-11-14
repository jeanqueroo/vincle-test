Ejecute el archivo run.sh y luego puede desde un navegador puede ingresar a la aplicacion mediante esta ruta:
http://localhost:80

las url del back son las siguiente.

GET by item code: http://localhost:8080/api/v1/item/DRINK-1

POST: http://localhost:8080/api/v1/item
    body: 
	{
    "itemType":"DRINK",
    "needFridge": true,
    "weight":100,
    "containerType": "PLASTIC",
    "name": "name"
   }
DELETE by item code: http://localhost:8080/api/v1/item/DRINK-1

UPDATE by item code: http://localhost:8080/api/v1/item/DRINK-1
   body: 
   {
    "needFridge": true,
    "weight":1000,
    "containerType": "PLASTIC",
    "name": "name juan",
    "state": "WAITING"
}

GET: http://localhost:8080/api/v1/item?ItemType=DRINK&code=DRINK-1&state=WAITING