# Agriculture Project
## Conception of  Project : 
### Our Class Diagram : 

### User Management (`Islem Work`):
![GitHub Logo](conception/Class_Islem.PNG)

### Equipment Management (`Yesser Work`):
![GitHub Logo](conception/Class_yesser.PNG)

### Product Management (`Wahb Work`):
![GitHub Logo](conception/DiagramClass.PNG)

### Product Management (`Mouna Work`):
![GitHub Logo](conception/mouna.PNG)

### Our Use Case Diagram : 

### User Management (`Islem Work`):
![GitHub Logo](conception/islem1.PNG)

### You can find all of diagrams [HERE](conception/)


## Configuration envirement :
### 1st STEP : Clone the project :
```console
git clone https://github.com/cherchariwahb/Micro_service_Prooject.git
```
### 2nd STEP :`Don't forget : Maven Update`
### 3rd STEP : 
#### Run Eureka project (GROUP Work)
Make sur you run project on `--server.port=8761`
#### Run blaq project (WAHB Work)
Make sur you run project on `--server.port=8080`
#### Run yesser_copie project (YESSER Work)
Make sur you run project on `--server.port=8082`
#### Run islem project (ISLEM Work)
Make sur you run project on `--server.port=8081`
#### Run mouna project (MOUNA Work)
Make sur you run project on `--server.port=8083`
#### Run Zuul project (GROUP Work)
Make sur you run project on `--server.port=8762`
### You find a result Like this (^_^)
![GitHub Logo](conception/eureka.PNG)

## Testing Project : `using postman`
### Get all products 
```console
http://localhost:8080/projet/categories/1/produits/
```
![GitHub Logo](conception/ProductsGet.PNG)




