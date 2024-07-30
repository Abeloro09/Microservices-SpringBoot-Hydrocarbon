# README

![logo](https://cdn.pixabay.com/photo/2021/07/25/18/02/api-6492491_960_720.jpg "logo")
## Project Overview



This project provides a backend API for calculating static volumes of hydrocarbons in storage tanks according to the American Petroleum Institute (API) standards. The architecture of the system is based on microservices, ensuring modularity and scalability. The microservices involved in this system are as follows:


- **api_calculation**: This microservice includes a utility class implementing the mathematical model as per the API standard. Its primary function is to perform volume calculations based on information provided to this microservice and data retrieved from the `tank_service` microservice.

- **tank_service**: This microservice handles CRUD operations for specific tank data and supplies necessary tank information to the `api_calculation` microservice.

- **eureka_server**: Serves as the service registry for discovering and managing services.

- **api_gateway**: Acts as the routing server where routing rules and security policies are applied.
- **servicio_oauth**: Manages user authentication and authorization.


## Business Logic


- **Volume Calculations**: Each calculation represents either the initial or final state of a receipt or dispatch of a storage tank. The difference between the initial and final calculations determines the volume of hydrocarbons received or dispatched.

- **Movements (Movimientos)**: A calculation involving both an initial and final condition is defined as a "movement."

- **Loads (Cargures)**: A set of multiple movements is classified as a "load," as typically several tank movements are involved in a load/unload operation to a bulk tank.

## Requisitos y Uso de Swagger UI

Para interactuar con los microservicios proporcionados por esta API, sigue estos pasos:

1. **Requisitos de Java**:
   - Asegúrate de que cada microservicio esté ejecutándose con Java 17. Esta versión es necesaria para garantizar la compatibilidad y el funcionamiento correcto de todos los servicios.

2. **Verificación del Estado de los Microservicios**:
   - Verifica que cada microservicio esté corriendo correctamente. Puedes hacerlo comprobando los logs o usando herramientas de monitoreo para asegurarte de que todos los servicios están activos y sin errores.


## 3. Swagger UI

To avoid the tedious process of authentication/authorization, route typing, JSON form typing, among other aspects required by some requests, an automatic API interface was implemented using Swagger UI. This interface allows for easy and convenient interaction with the endpoints of the main service api_calculation, displaying available endpoints and automatically providing the necessary JSON and parameters for each request. To access the automatic Swagger UI:

- **Swagger UI URL**: `http://localhost:8080/swagger-ui.html`

### Using Swagger UI

**3.1. Create a Load (Cargue)**: Execute a POST request on the `cargue-controller`. No parameters or JSON in the body are required. This operation returns a JSON with an empty list of movements and other details.

`{
  "id": 1,
  "referencia": null,
  "referenciaCliente": null,
  "comprador": null,
  "vendedor": null,
  "destino": null,
  "inspector": null,
  "terminal": null,
  "nombreBuque": null,
  "fecha": null,
  "instrucciones": null,
  "listaMovimientos": []
}`

**3.2. Create a Movement**: Execute a POST request on the `movimiento-controller`. Provide the load ID for which the movement should be created. The response includes a JSON with two "liquidations" (initial and final) and additional data.

`{
  "id": 1,
  "difTOV": 0,
  "difFw": 0,
  "difGsv": 0,
  "difNsv": 0,
  "listaLIquidaciones": [
    {
      "id": 1,
      "gauge": 0,
      "tov": 0,
      "waterGauge": 0,
      "waterTov": 0,
      "fra": 0,
      "tempL": 0,
      "ctsh": 0,
      "gov": 0,
      "api60": 0,
      "ctl": 0,
      "gsv": 0,
      "bsw": 0,
      "nsv": 0,
      "nombreTk": null,
      "water": 0,
      "sediment": 0,
      "salt": 0,
      "sulfur": 0,
      "tan": 0,
      "viscosity": 0,
      "flashpoint": 0,
      "fecha": null,
      "hora": null,
      "kfra2": 0,
      "tamb": 0,
      "tlam": 0,
      "abd": "A",
      "kfra1": 0
    },
    {
      "id": 2,
      "gauge": 0,
      "tov": 0,
      "waterGauge": 0,
      "waterTov": 0,
      "fra": 0,
      "tempL": 0,
      "ctsh": 0,
      "gov": 0,
      "api60": 0,
      "ctl": 0,
      "gsv": 0,
      "bsw": 0,
      "nsv": 0,
      "nombreTk": null,
      "water": 0,
      "sediment": 0,
      "salt": 0,
      "sulfur": 0,
      "tan": 0,
      "viscosity": 0,
      "flashpoint": 0,
      "fecha": null,
      "hora": null,
      "kfra2": 0,
      "tamb": 0,
      "tlam": 0,
      "abd": "A",
      "kfra1": 0
    }
  ]
}`


**3.3. Calculate a Liquidation**: Use the PUT method (`calcularLiquidacion`) on the `movimiento-controller`. Submit a JSON with necessary calculation values, including the tank ID (`idTank`). The database contains five registered tanks, so valid IDs are: 1, 2, 3, 4, or 5. The response will include detailed calculation data for the liquidation. The possible values for the key "abd" are: "A", "B", and "D" depending on the type of hydrocarbon to be stored.

Example of JSON required in the HTTP request body:

`{
  "abd": "A",
  "api": 20,
  "flashpoint": 40,
  "gauge": 8945,
  "idTank": 2,
  "salt": 5.6,
  "sediment": 0.01,
  "sulfur": 1.7,
  "tamb": 85.0,
  "tan": 0.4,
  "tempL": 92.0,
  "tov": 187000,
  "viscosity": 72,
  "water": 0.285,
  "waterGauge": 80,
  "waterTov": 200
}`

Response example:

`{
  "id": 1,
  "difTOV": 187000,
  "difFw": 200,
  "difGsv": 184519.48,
  "difNsv": 183975.15,
  "listaLIquidaciones": [
    {
      "id": 1,
      "gauge": 8945,
      "tov": 187000,
      "waterGauge": 80,
      "waterTov": 200,
      "fra": 1.22,
      "tempL": 92,
      "ctsh": 1.00038,
      "gov": 186872.204,
      "api60": 20,
      "ctl": 0.98741,
      "gsv": 184519.48,
      "bsw": 0.295,
      "nsv": 183975.15,
      "nombreTk": "502",
      "water": 0,
      "sediment": 0,
      "salt": 0,
      "sulfur": 0,
      "tan": 0,
      "viscosity": 0,
      "flashpoint": 0,
      "fecha": null,
      "hora": null,
      "kfra2": 12.21,
      "tamb": 85,
      "tlam": 60,
      "abd": "A",
      "kfra1": 22
    },
    {
      "id": 2,
      "gauge": 0,
      "tov": 0,
      "waterGauge": 0,
      "waterTov": 0,
      "fra": 0,
      "tempL": 0,
      "ctsh": 0,
      "gov": 0,
      "api60": 0,
      "ctl": 0,
      "gsv": 0,
      "bsw": 0,
      "nsv": 0,
      "nombreTk": null,
      "water": 0,
      "sediment": 0,
      "salt": 0,
      "sulfur": 0,
      "tan": 0,
      "viscosity": 0,
      "flashpoint": 0,
      "fecha": null,
      "hora": null,
      "kfra2": 0,
      "tamb": 0,
      "tlam": 0,
      "abd": "A",
      "kfra1": 0
    }
  ]
}`


 
**3.4. Edit a Calculated Liquidation**: Use the PUT method (`editarLiquidacion`) on the `movimiento-controller`. Submit a JSON with the values to modify existing liquidation data. The response will provide the updated liquidation information.

`{
  "abd": "string",
  "api60": 0,
  "bsw": 0,
  "ctl": 0,
  "ctsh": 0,
  "flashpoint": 0,
  "fra": 0,
  "gauge": 0,
  "gov": 0,
  "gsv": 0,
  "kfra1": 0,
  "kfra2": 0,
  "nombreTk": "string",
  "nsv": 0,
  "salt": 0,
  "sediment": 0,
  "sulfur": 0,
  "tamb": 0,
  "tan": 0,
  "tempL": 0,
  "tlam": 0,
  "tov": 0,
  "viscosity": 0,
  "water": 0,
  "waterGauge": 0,
  "waterTov": 0
}`

## Security

The API employs Spring Security to enforce basic security protocols. It includes two predefined users:

- **andres**: Assigned the role of "user"

- **admin**: Assigned the role of "administrator"


Authentication and authorization are required to access various API routes.

### Accessing API Gateway and Obtaining Tokens

To access the API Gateway and obtain access tokens, follow these steps:

- **URL**: `http://localhost:8090/security/oauth/token`

- **Method**: POST

- **Headers**: "Authorization" / Basic Auth

  - `username`: frontendapp
  - `password`: 12345
  
- **Body**:

  - `username`: (either andres or admin)
  - `password`: 12345
  - `grant_type`: password

### Accessing Endpoints

After obtaining the access token, use it to access endpoints by including it in the "Authorization" / Bearer header. Consult the `SpringSecurityConfig` class within the `api_gateway` microservice for details on available routes and methods.


## Eureka Manager

To access the Eureka Service Registry, use:

- **URL**: `http://localhost:8761/`


## Documentation

Detailed Javadoc HTML documentation for the `api_calculation` microservice, which includes the core business logic and mathematical modeling, has been created and will be attached.

# Author

This project was developed by Abelardo Orozco. For further inquiries, please contact via email: abel.oro.dev@gmail.com.
