# Base Crud
Base Crud provides skeleton for Spring boot project from Controller to Data access layer.
## Documents
For more information, see "[Base Crud docs](https://97lynk.github.io/base-modules/base-crud/)"
## Features
### Data access layer
Base Crud using [Spring Data JPA](https://spring.io/projects/spring-data-jpa) to deal with database.
Characters:
- Extend class `BaseEntity<ID>` to get config `@Entity` and `@Id` with auto increment strategy
- `BaseDto<ID>`
- Extend class `BaseRepository<E>` to get crud actions.

### Business logic layer
Characters:
- Extend abstract class `BaseServiceImpl<E, D, R>` and override`toDto`, `toEntity` methods to mapping data flexibly. Overriding  `validateToInsert`, `validateToUpdate`, `validateToDelete` to validate before insert, update, delete action respectively.
### API layer
Characters:
- `BaseControllerImpl<D, S>`
### Exception handler
## Structures
```
├───controller
│   │   BaseController.java
│   │
│   └───impl
│           BaseControllerImpl.java
│
├───exception
│       BizException.java
│       DataException.java
│       ErrorConstant.java
│       ErrorInfo.java
│       ExceptionCatcher.java
│
├───jpa
│       BaseDto.java
│       BaseEntity.java
│       BaseRepository.java
│
└───service
    │   BaseService.java
    │
    └───impl
            BaseServiceImpl.java
```
