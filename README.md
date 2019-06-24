# Table of Contents
- [Table of Contents](#table-of-contents)
- [Introduction](#introduction)
- [Full-stack Applications](#full-stack-applications)
  - [E-commerce (shopping cart)](#e-commerce-shopping-cart)
    - [Server side implementations](#server-side-implementations)
    - [Client side implementations](#client-side-implementations)
  - [Blog/CMS](#blogcms)
    - [Server side implementations](#server-side-implementations-1)
    - [Client side](#client-side)
      - [The next come are](#the-next-come-are)
  - [Simple CRUD(Create, Read, Update, Delete)](#simple-crudcreate-read-update-delete)
    - [Server side implementations](#server-side-implementations-2)
    - [Client side implementations](#client-side-implementations-1)
      - [The next come are](#the-next-come-are-1)
  - [CRUD + Pagination](#crud--pagination)
    - [Server side implementations](#server-side-implementations-3)
      - [The next come are](#the-next-come-are-2)
    - [Client side implementations](#client-side-implementations-2)
      - [The next come are](#the-next-come-are-3)
- [Social media links](#social-media-links)
- [Commands used to build the project](#commands-used-to-build-the-project)
- [Follow me](#follow-me)
    
# Introduction
An API sample showing how to create a basic Rest API and implement the CRUD operations
using Spring Security OAuth2 with JWT. First you have to obtain a JWT, you can go either to /oauth/token
or to /auth/login, providing client_username:client_password form for http basic authentication as well as post body with username and
password.
Example: POST client1:password@localhost:8080/auth/login
With client1:password as the client credentials. And {"username": "admin": "password":"password"} in the post Body.
For more details and other examples look at the postman_collection.json file attached with this repo

# What you will learn:
- Spring Boot
- Spring Data
- Spring Security
- Spring Security OAuth2 + JWT
- Hql
- Pagination
- Sorting
- Full CRUD
- Customizing Jackson JSON responses and strategies.
- H2 integration
- Seeding data with Faker
- Organizing applications


# Full-stack Applications
## Simple Crud
### Server side implementations
- [Python Django + Rest Framework](https://github.com/melardev/DjangoRestFrameworkCrud)
- [Python Django](https://github.com/melardev/DjanogApiCrud)
- [Python Flask](https://github.com/melardev/FlaskApiCrud)
- [Asp.Net Core](https://github.com/melardev/AspNetCoreApiCrud)
- [Asp.Net Core + MediatR](https://github.com/melardev/AspNetCoreApiCrudMediatR)
- [Asp.Net Core + NHibernate](https://github.com/melardev/.NetCoreApiNHibernateCrud)
- [Asp.Net Core + ADO.Net](https://github.com/melardev/.NetCoreApiADO.NetCrud)
- [Asp.Net Core + Dapper](https://github.com/melardev/.NetCoreApiDapperCrud)
- [Asp.Net Web Api 2](https://github.com/melardev/AspNetWebApiCrud)
- [Asp.Net Web Api 2 + NHibernate](https://github.com/melardev/.NetWebApiNHibernateCrud)
- [Asp.Net Web Api 2 + ADO.Net](https://github.com/melardev/.NetWebApiADO.NetCrud)
- [Asp.Net Web Api 2 + Autofac](https://github.com/melardev/.NetWebApiAutofac)
- [Asp.Net Web Api 2 + Dapper](https://github.com/melardev/.NetWebApiDapperCrud)
- [Laravel](https://github.com/melardev/LaravelApiCrud)
- [Ruby On Rails](https://github.com/melardev/RailsApiCrud)
- [Ruby On Rails + JBuilder](https://github.com/melardev/RailsApiJBuilderCrud)
- [Spring Boot + Spring Data JPA](https://github.com/melardev/SpringBootApiJpaCrud)
- [Spring Boot + Spring Data MonoDb](https://github.com/melardev/JavaSpringBootApiMongoCrud)
- [Spring Boot + Reactive Spring Data MonoDb + Basic Auth](https://github.com/melardev/JavaSpringBootRxApiRxMongoRxHttpBasicCrud)
- [Kotlin Spring Boot + Reactive Spring Data MonoDb + Basic Auth](https://github.com/melardev/KotlinSpringBootRxApiRxMongoRxHttpBasicCrud)
- [Kotlin Spring Boot + Spring Data MonoDb](https://github.com/melardev/KotlinSpringBootApiMongoCrud)
- [Kotlin Spring Boot + Spring Data JPA](https://github.com/melardev/KotlinSpringBootApiJpaCrud)
- [Spring Boot + JAX-RS(Jersey) + Spring Data JPA](https://github.com/melardev/SpringBootApiJerseySpringDataCrud)
- [Spring Boot Reactive + MongoDB Reactive](https://github.com/melardev/SpringBootApiReactiveMongoCrud)
- [Kotlin Spring Boot Reactive + MongoDB Reactive](https://github.com/melardev/KotlinSpringBootRxApiRxMongoCrud)
- [Java Spring Boot Web Reactive + Spring Data](https://github.com/melardev/JavaSpringBootApiRxHybridCrud)
- [Kotlin Spring Boot Web Reactive + Spring Data](https://github.com/melardev/KotlinSpringBootApiRxHybridCrud)
- [Go + GORM](https://github.com/melardev/GoGormApiCrud)
- [Go + GinGonic + GORM](https://github.com/melardev/GoGinGonicApiGormCrud)
- [Go + Gorilla + GORM](https://github.com/melardev/GoMuxGormApiCrud)
- [Go + Beego(Web and ORM)](https://github.com/melardev/GoBeegoApiCrud)
- [Go + Beego + GORM](https://github.com/melardev/GoBeegoGormApiCrud)
- [Express.JS + Sequelize ORM](https://github.com/melardev/ExpressSequelizeApiCrud)
- [Express.JS + BookShelf ORM](https://github.com/melardev/ExpressBookshelfApiCrud)
- [Express.JS + Mongoose](https://github.com/melardev/ExpressMongooseApiCrud)

#### Microservices
- [Java Spring Boot Zuul + Rest](https://github.com/melardev/JavaSpringBootZuulRestApiCrud)
- [Kotlin Spring Boot Zuul + Rest](https://github.com/melardev/KotlinSpringBootZuulRestApiCrud)
- [Java Spring Cloud Eureka + Gateway + EurekaClient Proxy + Rest](https://github.com/melardev/Java_SpringCloud_Eureka_Gateway_EurekaProxy_RestCrud)
- [Java Spring Cloud Eureka + Gateway + LoadBalancedRest Proxy + Rest](https://github.com/melardev/Java_SpringCloud_Eureka_Gateway_LBRestProxy_RestCrud)
- [Java Spring Cloud Eureka + Gateway + Cloud Stream RabbitMQ + Admin + Rest](https://github.com/melardev/Java_SpringCloud_Eureka_Gateway_CloudStreamRabbitMQ_Admin_RestCrud)
- [Java Spring Cloud Eureka + Gateway + Config + Rest Swagger](https://github.com/melardev/Java_SpringCloud_Eureka_Gateway_Config_RestSwaggerCrud)
- [Java Spring Cloud Eureka + Gateway + Admin + Cloud Stream RabbitMQ + RxProxy + RxRest](https://github.com/melardev/Java_SpringCloud_Eureka_Gateway_Admin_CloudStreamRabbitMQ_RxProxy_RxRestCrud)
- [Java Spring Cloud Eureka + Gateway + Webflux + RxMongoDB + Rx Proxy with WebClient](https://github.com/melardev/Java_SpringCloud_Eureka_Gateway_RxWeb_RxMongoDb_RxProxy)
- [Java Spring Cloud Eureka + Zuul + Config + Kafka + Rest](https://github.com/melardev/Java_SpringCloud_Eureka_Zuul_Config_Kafka_RestCrud)
- [Java Spring Cloud Eureka + Zuul + Config + Hystrix + Turbine + Feign + Rest](https://github.com/melardev/Java_SpringCloud_Eureka_Zuul_Config_Hystrix_Turbine_Feign_RestCrud)
- [Java Spring Cloud Eureka + Zuul + Feign + Sleuth + Zipkin + Rest](https://github.com/melardev/Java_SpringCloud_Eureka_Zuul_Feign_Sleuth_Zipkin_RestCrud)
- [Java Spring Cloud Eureka + Zuul + Admin + Rest](https://github.com/melardev/Java_SpringCloud_Eureka_Zuul_Admin_RestCrud)
- [Java Spring Cloud Eureka + Gateway + Config + Cloud Bus RabbitMQ + Rest](https://github.com/melardev/Java_SpringCloud_Eureka_Gateway_Config_CloudBusRabbitMQ_RestCrud)
- [Java Spring Cloud Eureka + Zuul + Rest + LoadBalancer Proxy](https://github.com/melardev/Java_SpringCloud_Eureka_Zuul_LoadBalancerProxy_RestCrud)
- [Java Spring Cloud Eureka + Config Server + Zuul + Kafka + Discovery Client Proxy + Rest](https://github.com/melardev/Java_SpringCloud_Eureka_Zuul_Config_Kafka_ProxyDiscovery_RestCrud)

### Client side implementations
- [React](https://github.com/melardev/ReactCrudAsync)
- [React + Redux](https://github.com/melardev/ReactReduxAsyncCrud)
- [Angular](https://github.com/melardev/AngularApiCrud)
- [Vue](https://github.com/melardev/VueAsyncCrud)
- [Vue + Vuex](https://github.com/melardev/VueVuexAsyncCrud)

#### The next come are
- Angular NgRx-Store
- Angular + Material
- React + Material
- React + Redux + Material
- Vue + Material
- Vue + Vuex + Material
- Ember
- Vanilla javascript

## Crud + Pagination
### Server side implementations
- [AspNet Core](https://github.com/melardev/AspNetCoreApiPaginatedCrud)
- [Asp.Net Core + NHibernate](https://github.com/melardev/.NetCoreApiNHibernateCrudPagination)
- [Asp.Net Core + MediatR](https://github.com/melardev/AspNetCoreApiPaginatedCrudMediatR)
- [Asp.Net Core + ADO.Net](https://github.com/melardev/.NetCoreApiADO.NetCrudPagination)
- [Asp.Net Core + Dapper](https://github.com/melardev/.NetCoreApiDapperCrudPagignation)
- [Asp.Net Web Api 2](https://github.com/melardev/WebApiPaginatedAsyncCrud)
- [Asp.Net Web Api 2 + NHibernate](https://github.com/melardev/.NetWebApiNHibernateCrudPagination)
- [Asp.Net Web Api 2 + ADO.Net](https://github.com/melardev/.NetWebApiADO.NetCrudPagination)
- [Asp.Net Web Api 2 + Autofac](https://github.com/melardev/.NetWebApiAutofacPagination)
- [Asp.Net Web Api 2 + Dapper](https://github.com/melardev/.NetWebApiDapperCrudPagination)
- [Spring Boot + Spring Data + Jersey](https://github.com/melardev/SpringBootJerseyApiPaginatedCrud)
- [Spring Boot + Spring Data](https://github.com/melardev/SpringBootApiJpaPaginatedCrud)
- [Spring Boot + Spring Data MonoDb](https://github.com/melardev/JavaSpringBootApiMongoCrudPagination)
- [Kotlin Spring Boot + Spring Data MonoDb](https://github.com/melardev/KotlinSpringBootApiMongoCrudPagination)
- [Spring Boot Reactive + Spring Data Reactive](https://github.com/melardev/ApiCrudReactiveMongo)
- [Java Spring Boot Web Reactive + Spring Data](https://github.com/melardev/JavaSpringBootApiRxHybridCrudPagination)
- [Kotlin Spring Boot Reactive + MongoDB Reactive](https://github.com/melardev/KotlinSpringBootRxApiRxMongoCrudPagination)
- [Kotlin Spring Boot Web Reactive + Spring Data](https://github.com/melardev/KotlinSpringBootApiRxHybridCrudPagination)
- [Spring Boot + Reactive Spring Data MonoDb + Basic Auth](https://github.com/melardev/JavaSpringBootRxApiRxMongoRxHttpBasicCrudPagination)
- [Kotlin Spring Boot + Reactive Spring Data MonoDb + Basic Auth](https://github.com/melardev/KotlinSpringBootRxApiRxMongoRxHttpBasicCrudPagination)
- [Go + GORM](https://github.com/melardev/GoGormApiCrudPagination)
- [Go + Gin Gonic + GORM](https://github.com/melardev/GoGinGonicApiPaginatedCrud)
- [Go + Gorilla + GORM](https://github.com/melardev/GoMuxGormApiCrudPagination)
- [Go + Beego(Web and ORM)](https://github.com/melardev/GoBeegoApiCrudPagination)
- [Go + Beego(Web) + GORM)](https://github.com/melardev/GoBeegoGormApiCrudPagination)
- [Laravel](https://github.com/melardev/LaravelApiPaginatedCrud)
- [Rails + JBuilder](https://github.com/melardev/RailsJBuilderApiPaginatedCrud)
- [Rails](https://github.com/melardev/RailsApiPaginatedCrud)
- [NodeJs Express + Sequelize](https://github.com/melardev/ExpressSequelizeApiPaginatedCrud)
- [NodeJs Express + Bookshelf](https://github.com/melardev/ExpressBookshelfApiPaginatedCrud)
- [NodeJs Express + Mongoose](https://github.com/melardev/ExpressApiMongoosePaginatedCrud)
- [Python Django](https://github.com/melardev/DjangoApiCrudPaginated)
- [Python Django + Rest Framework](https://github.com/melardev/DjangoRestFrameworkPaginatedCrud)
- [Python Flask](https://github.com/melardev/FlaskApiPaginatedCrud)


#### MicroServices
- [Java Spring Boot Zuul + Rest](https://github.com/melardev/JavaSpringBootZuulRestApiPaginatedCrud)
- [Kotlin Spring Boot Zuul + Rest](https://github.com/melardev/KotlinSpringBootZuulRestApiPaginatedCrud)

#### The next come are
- NodeJs Express + Knex
- Flask + Flask-Restful
- Laravel + Fractal
- Laravel + ApiResources
- Go with Mux
- AspNet Web Api 2
- Jersey
- Elixir

### Client side implementations
- [Angular](https://github.com/melardev/AngularPaginatedAsyncCrud)
- [React-Redux](https://github.com/melardev/ReactReduxPaginatedAsyncCrud)
- [React](https://github.com/melardev/ReactAsyncPaginatedCrud)
- [Vue + Vuex](https://github.com/melardev/VueVuexPaginatedAsyncCrud)
- [Vue](https://github.com/melardev/VuePaginatedAsyncCrud)


#### The next come are
- Angular NgRx-Store
- Angular + Material
- React + Material
- React + Redux + Material
- Vue + Material
- Vue + Vuex + Material
- Ember
- Vanilla javascript


## Auth Jwt + Crud
### Server side implementations
- [Spring Boot](https://github.com/melardev/JavaSpringBootJwtCrudPagination)
- [Spring Boot + OAuth with JWT](https://github.com/melardev/JavaSpringBootOAuth2JwtCrud)

## Auth Jwt + Crud + Pagination
### Server side implementations
- [Spring Boot](https://github.com/melardev/JavaSpringBootJwtCrudPagination)
- [Spring Boot + OAuth with JWT](https://github.com/melardev/JavaSpringBootOAuth2JwtCrudPagination)

### Client side implementations


## E-commerce
### Server side implementations
- [Spring Boot + Spring Data Hibernate](https://github.com/melardev/SBootApiEcomMVCHibernate)
- [Spring Boot + JAX-RS Jersey + Spring Data Hibernate](https://github.com/melardev/SpringBootEcommerceApiJersey)
- [Node Js + Sequelize](https://github.com/melardev/ApiEcomSequelizeExpress)
- [Node Js + Bookshelf](https://github.com/melardev/ApiEcomBookshelfExpress)
- [Node Js + Mongoose](https://github.com/melardev/ApiEcomMongooseExpress)
- [Python Django](https://github.com/melardev/DjangoRestShopApy)
- [Flask](https://github.com/melardev/FlaskApiEcommerce)
- [Golang go gonic](https://github.com/melardev/api_shop_gonic)
- [Ruby on Rails](https://github.com/melardev/RailsApiEcommerce)
- [AspNet Core](https://github.com/melardev/ApiAspCoreEcommerce)
- [Laravel](https://github.com/melardev/ApiEcommerceLaravel)

The next to come are:
- Spring Boot + Spring Data Hibernate + Kotlin
- Spring Boot + Jax-RS Jersey + Hibernate + Kotlin
- Spring Boot + mybatis
- Spring Boot + mybatis + Kotlin
- Asp.Net Web Api v2
- Elixir
- Golang + Beego
- Golang + Iris
- Golang + Echo
- Golang + Mux
- Golang + Revel
- Golang + Kit
- Flask + Flask-Restful
- AspNetCore + NHibernate
- AspNetCore + Dapper

### Client side implementations
This client side E-commerce application is also implemented using other client side technologies:
- [React Redux](https://github.com/melardev/ReactReduxEcommerceRestApi)
- [React](https://github.com/melardev/ReactEcommerceRestApi)
- [Vue](https://github.com/melardev/VueEcommerceRestApi)
- [Vue + Vuex](https://github.com/melardev/VueVuexEcommerceRestApi)
- [Angular](https://github.com/melardev/AngularEcommerceRestApi)

## Blog/CMS
### Server side implementations
### Client side
#### The next come are
- Angular NgRx-Store
- Angular + Material
- React + Material
- React + Redux + Material
- Vue + Material
- Vue + Vuex + Material
- Ember

# Social media links
- [Youtube Channel](https://youtube.com/melardev) I publish videos mainly on programming
- [Blog](http://melardev.com) Sometimes I publish the source code there before Github
- [Twitter](https://twitter.com/@melardev) I share tips on programming
- [Instagram](https://instagram.com/melar_dev) I share from time to time nice banners
