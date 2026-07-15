# 🍔 Foodies — Food Delivery Application

Foodies is a full-stack online food delivery platform built with **Spring Boot**, **MongoDB**, and **React**. It consists of three independent applications working together: a customer-facing web app, an admin panel for managing the platform, and a secure REST API backend.

---

## 📖 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Architecture](#-project-architecture)
- [Screenshots](#-screenshots)
- [Getting Started](#-getting-started)
- [API Overview](#-api-overview)
- [Environment Variables](#-environment-variables)
- [Deployment](#-deployment)
- [Folder Structure](#-folder-structure)
- [Future Improvements](#-future-improvements)

---

## 🧾 Overview

Foodies lets customers browse a food menu, add items to their cart, place orders, and pay securely online — while restaurant admins manage the food catalog and track incoming orders from a dedicated admin dashboard. The backend exposes a secure, stateless REST API protected with JWT-based authentication, and all food images are stored on AWS S3.

This project was built as a hands-on learning exercise in designing and securing a production-style full-stack application — covering authentication, authorization, payment integration, file storage, and API design end to end.

---

## ✨ Features

**Customer App**
- Browse food items by category with images, descriptions, and pricing
- User registration & login (JWT-secured)
- Add / remove items from cart with live quantity updates
- Secure checkout and payment via Razorpay
- Order history and order tracking

**Admin Panel**
- Add, view, and delete food items (with image upload to AWS S3)
- View all customer orders
- Update order status (e.g., Preparing → Out for Delivery → Delivered)

**Backend API**
- Stateless JWT authentication & authorization with Spring Security
- BCrypt password hashing
- RESTful endpoints for users, food items, cart, and orders
- MongoDB for flexible, document-based data storage
- Razorpay integration for order payments and payment verification
- AWS S3 integration for food image storage
- Centralized CORS configuration for frontend–backend communication

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| **Backend** | Java, Spring Boot, Spring Security, Spring Data MongoDB |
| **Authentication** | JWT (JJWT library), BCrypt |
| **Database** | MongoDB |
| **File Storage** | AWS S3 |
| **Payments** | Razorpay |
| **Customer Frontend** | React, Vite, Bootstrap, Axios, React Router |
| **Admin Panel** | React, Vite, Bootstrap, Axios, React Router |
| **Build Tool** | Maven |

---

## 🏗 Project Architecture

```
                     ┌───────────────────┐
                     │   MongoDB Atlas   │
                     └─────────▲─────────┘
                               │
                     ┌─────────┴─────────┐
                     │   Spring Boot API │
                     │    (foodiesapi)   │
                     └───▲──────────▲────┘
                         │          │
             REST/JSON   │          │   REST/JSON
                         │          │
          ┌──────────────┘          └──────────────┐
          │                                         │
┌─────────┴─────────┐                    ┌──────────┴─────────┐
│   Customer App     │                    │    Admin Panel     │
│     (foodies)       │                    │   (adminpanel)     │
└────────────────────┘                    └────────────────────┘
```

- **`foodiesapi`** — Spring Boot REST API (business logic, authentication, database access)
- **`foodies`** — Customer-facing React application
- **`adminpanel`** — Admin-facing React application

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Maven
- Node.js & npm
- MongoDB (local instance or MongoDB Atlas)
- AWS S3 bucket & credentials
- Razorpay account (test mode keys work fine for development)

### 1. Backend Setup (`foodiesapi`)

```bash
cd foodiesapi
```

Configure `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=${MONGODB_URI:mongodb://localhost:27017/foodies}

aws.access.key=${AWS_ACCESS_KEY}
aws.secret.key=${AWS_SECRET_KEY}
aws.region=${AWS_REGION}
aws.s3.bucketname=${AWS_BUCKET_NAME}

jwt.secret.key=${JWT_SECRET}

razorpay_key=${RAZORPAY_KEY}
razorpay_secret=${RAZORPAY_SECRET}
```

Run the application:

```bash
./mvnw spring-boot:run
```

The API will start on `http://localhost:8080`.

### 2. Customer App Setup (`foodies`)

```bash
cd foodies
npm install
npm run dev
```

### 3. Admin Panel Setup (`adminpanel`)

```bash
cd adminpanel
npm install
npm run dev
```

---

## 🔌 API Overview

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/register` | Register a new user | No |
| POST | `/api/login` | Authenticate user & return JWT | No |
| GET | `/api/foods` | Get all food items | No |
| GET | `/api/foods/{id}` | Get details of a specific food item | No |
| POST | `/api/foods` | Add a new food item (admin) | Yes |
| DELETE | `/api/foods/{id}` | Delete a food item (admin) | Yes |
| GET | `/api/cart` | Get current user's cart | Yes |
| POST | `/api/cart` | Add item to cart | Yes |
| POST | `/api/cart/remove` | Remove item quantity from cart | Yes |
| DELETE | `/api/cart` | Clear cart | Yes |
| POST | `/api/orders/create` | Create a new order | Yes |
| POST | `/api/orders/verify` | Verify Razorpay payment | Yes |
| GET | `/api/orders` | Get orders for logged-in user | Yes |
| GET | `/api/orders/all` | Get all orders (admin) | No* |
| PATCH | `/api/orders/status/{orderId}` | Update order status (admin) | No* |
| DELETE | `/api/orders/{orderId}` | Delete an order | Yes |

_*Admin-only endpoints are currently public for development convenience; role-based restriction is a planned improvement._

---

## 🔐 Environment Variables

The backend expects the following environment variables (used in `application.properties`):

| Variable | Description |
|---|---|
| `MONGODB_URI` | MongoDB connection string |
| `JWT_SECRET` | Secret key used to sign JWT tokens (256-bit minimum for HS256) |
| `AWS_ACCESS_KEY` | AWS IAM access key |
| `AWS_SECRET_KEY` | AWS IAM secret key |
| `AWS_REGION` | AWS region of the S3 bucket |
| `AWS_BUCKET_NAME` | S3 bucket name for storing food images |
| `RAZORPAY_KEY` | Razorpay API key |
| `RAZORPAY_SECRET` | Razorpay API secret |

The frontend apps (`foodies`, `adminpanel`) use:

| Variable | Description |
|---|---|
| `VITE_API_BASE_URL` | Base URL of the deployed backend API (falls back to `http://localhost:8080` for local development) |

---

## ☁️ Deployment

**Status: 🚧 Pending**

Deployment is planned using the following stack:

- **Database:** MongoDB Atlas (cloud-hosted)
- **Backend:** Render (Docker-based deployment)
- **Customer App & Admin Panel:** Vercel

This section will be updated with live deployment links once the deployment is complete.

---

## 📁 Folder Structure

```
online-food-delivery-project/
├── foodiesapi/          # Spring Boot backend
│   ├── src/main/java/.../controller
│   ├── src/main/java/.../service
│   ├── src/main/java/.../repository
│   ├── src/main/java/.../entity
│   ├── src/main/java/.../config
│   └── src/main/resources/application.properties
├── foodies/              # Customer-facing React app
│   └── src/
│       ├── components/
│       ├── pages/
│       └── service/
└── adminpanel/           # Admin React app
    └── src/
        ├── components/
        ├── pages/
        └── services/
```

---

## 🔮 Future Improvements

- Role-based access control (RBAC) for admin-only endpoints
- Email notifications for order status updates
- Unit and integration test coverage
- CI/CD pipeline for automated deployments
- Pagination and search on the food listing page

---

## 👤 Author

**Rupesh Kumar**
Built as part of an internship project to gain hands-on experience with full-stack development, authentication, and cloud deployment.

---

## 📸 Screenshots

<img width="1270" height="707" alt="Screenshot 2026-07-15 at 3 33 03 PM" src="https://github.com/user-attachments/assets/be7b5346-4152-418b-bed0-7ed326a19583" />

<img width="1276" height="708" alt="Screenshot 2026-07-15 at 3 29 35 PM" src="https://github.com/user-attachments/assets/b4e35162-046d-4665-ac15-d480e3b0e146" />

<img width="1275" height="707" alt="Screenshot 2026-07-15 at 3 31 04 PM" src="https://github.com/user-attachments/assets/4ac6c0b2-9d61-4d95-ae3c-691b3f065118" />

<img width="1270" height="706" alt="Screenshot 2026-07-15 at 3 33 44 PM" src="https://github.com/user-attachments/assets/fd484931-ed0b-426b-8107-8e40bad1ff38" />

<img width="1275" height="705" alt="Screenshot 2026-07-15 at 3 31 23 PM" src="https://github.com/user-attachments/assets/12c73757-24a2-4510-a492-dc735163d9e5" />

<img width="1271" height="707" alt="Screenshot 2026-07-15 at 3 34 06 PM" src="https://github.com/user-attachments/assets/67b8b7ca-0198-4f6d-91e0-62ae29cbcbf9" />

<img width="980" height="570" alt="Screenshot 2026-07-15 at 3 35 22 PM" src="https://github.com/user-attachments/assets/2114b78f-16aa-4385-ab6b-61cccafdbb3c" />

<img width="794" height="502" alt="Screenshot 2026-07-15 at 3 48 31 PM" src="https://github.com/user-attachments/assets/ecd61417-f023-4176-b3f0-fda8fd58a6c0" />

<img width="1280" height="800" alt="Screenshot 2026-07-15 at 3 44 24 PM" src="https://github.com/user-attachments/assets/6eb0cc7b-b64e-4a14-a872-d4fd37afbe74" />

<img width="1273" height="704" alt="Screenshot 2026-07-15 at 3 32 07 PM" src="https://github.com/user-attachments/assets/4f08bc51-f9b2-4a59-906c-9aad17641549" />

<img width="1272" height="706" alt="Screenshot 2026-07-15 at 3 32 19 PM" src="https://github.com/user-attachments/assets/bc5fd137-46c6-4adf-b843-877ed8ddd3b7" />

<img width="1276" height="695" alt="Screenshot 2026-07-15 at 3 32 38 PM" src="https://github.com/user-attachments/assets/a04c836b-a4df-44f4-91cd-3fa20cd60a68" />

---
