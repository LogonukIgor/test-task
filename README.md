# I-Banking

Добро пожаловать в мини-приложение "I-Banking"! Это приложение предоставляет возможность пользователям управлять своими банковскими счетами и совершать финансовые операции.

## Технологический стек

- Spring Boot 3.2 (AOP, MVC, Data JPA)
- Lombok
- Swagger
- Maven
- PostgreSQL

## Функциональность

1. **Регистрация пользователя:**
    - Пользователь может создать учетную запись, указав имя и уникальный логин.

2. **Управление счетами:**
    - После регистрации пользователь может открывать счета с различной валютой (RUB/USD/EUR).
    - При создании счета указывается 4-ёх значный PIN-код, который обеспечивает дальнейший доступ к счету.

3. **Финансовые операции:**
    - Доступны следующие операции: пополнение, списание и перевод средств на другой счет.

4. **Информационные эндпоинты:**
    - Получение всех учетных записей пользователя.
    - Получение всех транзакций для конкретного счёта.

## Документация и Swagger UI

Для удобства взаимодействия с API предоставлен Swagger UI. Вы можете посетить [Swagger UI](http://localhost:8080/swagger-ui/index.html#) для ознакомления с доступными эндпоинтами и их описанием.