# KitVerse

KitVerse is a group coursework project for the **Advanced Programming Technologies** module.  
It is a Java web application built with Jakarta Servlet/JSP and Maven, with MySQL as the backend database.

## Project Overview

KitVerse provides a basic e-commerce style workflow with:
- User registration, login, logout, and profile management
- Product and product variant browsing
- Cart and order placement
- Admin-only management pages for dashboard, products, variants, and orders

## Tech Stack

- Java 17
- Jakarta EE (Servlet/JSP)
- Maven (WAR packaging)
- MySQL
- JSTL
- jBCrypt (password hashing)

## Setup Instructions

1. **Clone the repository**
2. **Create the database**
   - Use one of the SQL scripts in `/sql/`:
     - `kitVerse-1(with Create database)sql.sql`
     - `kitVerse-2.sql`
3. **Configure database connection**
   - Current defaults are in `src/main/java/kitverse/utilities/DBConfig.java`:
     - DB name: `kitVerse`
     - Username: `root`
     - Password: empty
     - URL: `jdbc:mysql://localhost:3306/kitVerse`
4. **Build the project**
   ```bash
   mvn clean package
   ```
5. **Deploy the generated WAR** to a Jakarta-compatible servlet container (for example Tomcat).
6. **Open the application** and navigate to `/home`.

## Application Routes (High Level)

- Public: `/home`, `/login`, `/register`, `/aboutUs`, `/contactUs`
- User: `/product`, `/variant`, `/cart`, `/order`, `/profile`
- Admin: `/admin/dashboard`, `/admin/product`, `/admin/variant`, `/admin/orders`, `/upload`

Authentication and role checks are handled globally by `AuthFilter`.

## File Structure

```text
KitVerse/
├── pom.xml
├── sql/
│   ├── kitVerse-1(with Create database)sql.sql
│   └── kitVerse-2.sql
└── src/
    └── main/
        ├── java/kitverse/
        │   ├── dao/                # Database access classes
        │   ├── daoInterfaces/      # DAO contracts
        │   ├── filter/             # Authentication/authorization filter
        │   ├── models/             # Entity/model classes
        │   ├── servlets/           # Request handlers/controllers
        │   └── utilities/          # DB, session, validation, image, password helpers
        ├── resources/
        │   └── META-INF/
        └── webapp/
            ├── WEB-INF/
            │   ├── web.xml
            │   └── pages/
            │       ├── adminPages/ # Admin JSP pages
            │       └── *.jsp        # User-facing JSP pages
            ├── css/                 # Stylesheets
            ├── templates/           # Shared UI fragments
            ├── resources/           # Static assets
            └── error404.html
```

## Notes

- Default session timeout is configured in `web.xml` (30 minutes).
- If you change DB credentials or schema name, update `DBConfig.java` accordingly.
