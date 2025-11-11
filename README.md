## 💾 Personal-Basement Scaffold

This is a personal full-stack project base template integrating modern front-end and back-end technologies with a high-performance gateway. 

This scaffold provides pre-configured basic code and core dependencies to help developers quickly set up a project featuring **Spring Boot (Java)** for the backend, **Vite (Frontend)** for building, and **OpenResty (High-Performance Gateway)** for routing, and an easy-use **JSON Mocker** for data simulation.

### 🚀 Technology Stack Overview

**Spring Boot (Java)** 

This is the Spring Boot backend template, pre-configured with core dependencies and utility code.

* **Required JDK:** **Java 21 or Higher**.
* **Core Dependencies (via Maven):**
    * **`web`**: For building **RESTful APIs**，tranditional servlet.
    * **`lombok`**: To reduce boilerplate code (e.g., getters/setters).
    * **`spring-boot-validation`**: For **validating** request data.
    * **`mysql-connector-j`**&**`hikari-CP`**: MySQL database driver.
    * **`mybatis-flex`**: Simple ORM for database operations.

**Frontend** **Vite / TypeScript**

The frontend project is set up using Vite for a fast development experience and TypeScript for code robustness.

* **Build Tool:** **Vite** is used for its blazing-fast cold start and instant Hot Module Replacement (HMR).
* **Language:** **TypeScript**
* **Core Dependencies (via npm):**
    * **`element-plus`**: A popular component library for building the **User Interface (UI)**.
    * **`vue-router`**: The official library for **managing routing** and navigation within the SPA.
    * **`pinia`**: The recommended lightweight store for **State Management** (replacing Vuex).
    * **`gprogress`**: A utility for displaying **page loading progress** bars.
    * **`vue-i18n`**: Provides robust capabilities for **Internationalization** (multi-language support).
    * **`axios`**: A widely used library for making **HTTP requests** (API calls).
    * **`scss`**: The preprocessor used for **styling** the application.


**Gateway** **OpenResty (Lua)**

A high-performance web platform based on Nginx, used for reverse proxy, load balancing, and API routing.
