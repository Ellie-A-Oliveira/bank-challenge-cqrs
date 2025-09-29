# Bank Application

CQRS + Event Sourcing banking app built with **Spring Boot**, **Axon Framework**, **Redis**, and **PostgreSQL**.

## Features

- CQRS & Event Sourcing (Axon Framework)
- Accounts & Transactions aggregates
- Redis for read-side projections
- PostgreSQL for persistent storage
- JWT-based authentication
- Dockerized for easy setup

## Quick Start

1. Clone repositor
```
git clone https://github.com/Ellie-A-Oliveira/bank-challenge-cqrs
cd bank-challenge-cqrs
```
2. Start all services
```
docker-compose up --build
```
