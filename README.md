# 📇 Smart Contact - Contact Management System

A full-featured Contact Management System built with **Spring Boot**. This Smart Contact application is designed to demonstrate the complete development lifecycle using modern Java backend technologies. From user authentication to contact CRUD operations, this project also showcases REST API design, security best practices, and third-party payment integration.

---

## 🚀 Features

### ✅ Core Functionalities
- User registration and login with form validation
- Add, update, delete, and search contacts
- Contact grouping and tagging
- Profile management and file uploads

### 🔐 Security
- **Spring Security** integration
- Password encryption using BCrypt
- Role-based access control (Admin/User)
- CSRF protection
- Secure login sessions

### 🔑 Authentication
- JWT (JSON Web Token) authentication for REST APIs
- OAuth2 integration with Google & GitHub
- Token-based session management for frontend-backend communication

### 🔄 API (RESTful)
- RESTful API endpoints for user and contact management
- Postman Collection for testing
- Pagination and sorting support
- Error handling with proper HTTP status codes

### 💳 Payment Integration
- Payment gateway integration (Stripe/PayPal)
- Donate to unlock premium features (example: more contact storage)
- Transaction logging and status verification

---

## 🛠️ Tech Stack

| Layer | Technology |
|------|-------------|
| Language | Java 17 |
| Backend Framework | Spring Boot 3.x |
| View Layer | Thymeleaf |
| ORM | Hibernate / JPA |
| Database | MySQL |
| Security | Spring Security, OAuth2, JWT |
| API | Spring REST |
| Dev Tools | Spring DevTools, Lombok, Maven |
| Testing | JUnit, Mockito |
| Payment | Stripe/PayPal API |
| Frontend | Bootstrap 5, Thymeleaf, jQuery |

---

## 🧠 Concepts Covered

- Spring Boot Core (Beans, Autowiring, Profiles)
- Spring MVC (Controllers, ViewResolvers)
- Spring Data JPA (Repositories, Pagination, Sorting)
- Spring Security (JWT, OAuth2, CSRF, BCrypt)
- API Development (REST, JSON, Exception Handling)
- File Upload/Download Handling
- Custom Error Pages
- Email Integration (optional)
- Payment Gateway APIs
- Best Practices (Layered Architecture, DTOs, Service Layer, Entity Validation)

---

## 📷 UI Screenshots (Optional)

> Include images or GIFs showing login page, dashboard, contact list, contact add/edit form, payment screen, etc.

---

## 🔧 Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/smart-contact-system.git
cd smart-contact-system
