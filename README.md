# 🩸 BloodWave

<p align="center">
  <strong>BloodWave</strong> is a modern blood donation management platform designed to connect blood donors, hospitals, and transfusion centers while optimizing blood inventory management.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-red?style=for-the-badge&logo=openjdk" />
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/Spring_Security-JWT-success?style=for-the-badge&logo=springsecurity" />
  <img src="https://img.shields.io/badge/Redis-Cache-red?style=for-the-badge&logo=redis" />
  <img src="https://img.shields.io/badge/Docker-Containers-blue?style=for-the-badge&logo=docker" />
  <img src="https://img.shields.io/badge/Jenkins-CI/CD-orange?style=for-the-badge&logo=jenkins" />
</p>

---

# 📖 Overview

BloodWave is a secure and scalable blood donation management system that simplifies the communication between donors, hospitals, and blood banks.

The application enables healthcare institutions to efficiently manage blood inventories, donor information, blood requests, and donation history while ensuring data security and high performance.

---

# ✨ Key Features

## 👤 Donor Management

* Register and manage donor profiles
* Track donation history
* View eligibility status
* Blood type management

## 🏥 Hospital & Blood Center Management

* Manage hospitals and transfusion centers
* Blood stock monitoring
* Inventory updates
* Blood request management

## 🩸 Blood Donation

* Schedule donations
* Donation tracking
* Donation validation
* Blood availability updates

## 📦 Blood Inventory

* Real-time stock management
* Blood type categorization
* Availability monitoring
* Low stock alerts

## 🔐 Security

* JWT Authentication
* Spring Security
* Role-Based Access Control (RBAC)
* Secure REST APIs

---

# 🛠️ Tech Stack

| Technology      | Purpose                             |
| --------------- | ----------------------------------- |
| Java 17         | Backend Development                 |
| Spring Boot     | Application Framework               |
| Spring Security | Authentication & Authorization      |
| JWT             | Secure Authentication               |
| Spring Data JPA | Database Access                     |
| Hibernate       | ORM                                 |
| Redis           | Cache Layer                         |
| Docker          | Containerization                    |
| Jenkins         | Continuous Integration & Deployment |
| Maven           | Dependency Management               |

---

# 🏗️ Architecture

```
                Client
                   │
              REST API
                   │
         Spring Boot Backend
                   │
        ┌──────────┴──────────┐
        │                     │
     Redis Cache         Database
        │
   Faster Response
```

---

# 🔒 Security

BloodWave implements enterprise-grade authentication using:

* Spring Security
* JWT Authentication
* Stateless Sessions
* Password Encryption
* Role-Based Authorization

Supported roles include:

* Administrator
* Donor
* Hospital
* Blood Center

---

# ⚡ Performance

Redis is used to:

* Cache frequently requested data
* Reduce database load
* Improve API response time
* Increase scalability

---

# 🐳 Docker

The project is containerized using Docker, making deployment consistent across development, testing, and production environments.

Benefits include:

* Easy deployment
* Environment consistency
* Scalability
* Isolation

---

# 🚀 Continuous Integration / Continuous Deployment

Jenkins automates the software delivery pipeline.

Pipeline includes:

* Source Code Checkout
* Maven Build
* Unit Tests
* Packaging
* Docker Image Build
* Deployment

---

# 📂 Project Structure

```
BloodWave
│
├── src
│   ├── main
│   │   ├── java
│   │   ├── resources
│   │   └── ...
│   │
│   └── test
│
├── docker
├── Jenkinsfile
├── pom.xml
└── README.md
```

---

# 🚀 Getting Started

## Clone the repository

```bash
git clone https://github.com/wassim-oucouc/BloodWave.git
```

## Navigate to the project

```bash
cd BloodWave
```

## Build

```bash
mvn clean install
```

## Run

```bash
mvn spring-boot:run
```

---

# 🔑 Main Technologies

* Java 17
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* Redis
* Docker
* Jenkins
* Maven

---

# 🎯 Objectives

The project aims to:

* Improve blood donation management
* Reduce shortages through better inventory tracking
* Connect donors with healthcare institutions
* Secure sensitive medical data
* Provide a scalable architecture for future enhancements

---

# 📈 Future Improvements

* Email notifications
* SMS reminders
* Mobile application
* Real-time dashboard
* Analytics & Reporting
* Geo-location for nearby blood centers
* Appointment scheduling
* Emergency blood request system

---

# 👨‍💻 Author

**Wassim Oucouc**

Backend Java Developer

GitHub:
https://github.com/wassim-oucouc

---

# ⭐ Support

If you find this project useful, consider giving it a ⭐ on GitHub.

Your support is greatly appreciated!
