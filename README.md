# db_produccion — Microservicio de Producción (Corazón del ERP)

## ¿Qué hace?
Gestiona **órdenes de producción**, sus **piezas**, **telas**, **modelos de sacos (AB)** y **modelos de pantalones**.
Es el módulo principal del taller de sastrería Contreras.

## Stack
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA + Hibernate
- Spring Cloud OpenFeign (para llamadas a otros microservicios)
- Spring Cloud Netflix Eureka (cliente)

## Base de datos
- Contenedor: `erp-db-produccion-postgres` (postgres:16-alpine)
- DB: `db_produccion` (puerto host `5435` → contenedor `5432`)

## Tablas que crea
- `ordenes` (id, cliente_id, genero, tela_id, nombre_tela_imp, codigo_color, referencia_color, modelo_ab_id, modelo_pant_id, costo_total, costo_pagado, fecha_inicio, fecha_finalizacion, estado)
- `piezas_produccion` (id, orden_id, tipo_pieza, estado_pieza, encargado_id)
- `telas` (id, nombre)
- `modelos_ab` (id, nombre)
- `modelos_pant` (id, nombre)

## Endpoints

### Órdenes
| Método | Ruta                                            | Descripción                          |
|--------|-------------------------------------------------|--------------------------------------|
| GET    | `/api/ordenes`                                  | Lista órdenes activas del taller     |
| GET    | `/api/ordenes?clienteId=1`                      | Filtra órdenes por cliente           |
| GET    | `/api/ordenes/{id}`                             | Detalle de una orden + sus piezas    |
| POST   | `/api/ordenes`                                  | Crea una orden + sus piezas          |
| PATCH  | `/api/ordenes/{id}/estado`                      | Cambia estado de la orden            |
| PATCH  | `/api/ordenes/piezas/{piezaId}/estado`          | Cambia estado de una pieza           |
| PATCH  | `/api/ordenes/piezas/{piezaId}/asignar`         | Asigna un sastre a una pieza         |

### Catálogos
| Método | Ruta                          | Descripción                |
|--------|-------------------------------|----------------------------|
| GET    | `/api/catalogos/telas`        | Lista telas                |
| POST   | `/api/catalogos/telas`        | Crea tela                  |
| GET    | `/api/catalogos/modelos-ab`   | Lista modelos de sacos AB  |
| POST   | `/api/catalogos/modelos-ab`   | Crea modelo AB             |
| GET    | `/api/catalogos/modelos-pant` | Lista modelos de pantalones |
| POST   | `/api/catalogos/modelos-pant` | Crea modelo de pantalón    |

### Health
| GET    | `/api/ordenes/health`         | Healthcheck                |

## Estados de Orden
`por cortar` → `haciendose` → `por probar` → `por corregir` → `entregado`

## Estados de Pieza
`sin asignar` → `haciendo` → `terminado`

## Tipos de Pieza
`saco`, `pantalon`, `chaleco`, `falda`

## Cómo levantarlo
```bash
./mvnw clean package -DskipTests
docker compose up -d
```
Esto levanta Postgres (`5435`) y el microservicio (`8083`) juntos.

## Puerto
`8083`