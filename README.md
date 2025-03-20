# Visitor Management System - Backend

## 📌 Overview
This backend powers the **Visitor Management System**, providing secure authentication, visitor approvals, and real-time tracking. Built with **Spring Boot**, it follows **Microservices Architecture** with **API Gateway**, **Eureka Server**, and **RBAC (Role-Based Access Control)** for scalability and security.

## 🚀 Features
- 🔐 **Secure Authentication & RBAC**  
  - Admins can approve/reject visitors.  
  - Visitors can check their approval status.
- ⚡ **Microservices-Based Architecture**  
  - API Gateway for efficient request handling.  
  - Eureka Server for service discovery.  
  - Load Balancer for distributing traffic.
- 🔍 **Real-Time Monitoring & Error Handling**  
  - Eureka Server for service health checks.  
  - Global Exception Handling for debugging.
- 📍 **Smart Visitor Registration & Tracking**  
  - Dynamic visitor registration form with real-time validation.  
  - Auto-generated visitor ID for tracking.
- 🔄 **Dynamic & Configurable System**  
  - No hardcoded values; backend-controlled configurations.

## 🏗️ Architecture
The backend is structured as follows:
- **Authentication Service**: Manages user authentication and role-based access.
- **Visitor Management Service**: Handles visitor approvals and tracking.
- **API Gateway**: Routes requests securely.
- **Eureka Server**: Provides service discovery.
- **Load Balancer**: Ensures smooth traffic distribution.
  

## 📜 Tech Stack
- **Spring Boot** (Java)
- **Spring Security** (Authentication & RBAC)
- **Spring Cloud** (Microservices & Service Discovery)
- **MySQL** (Database)
- **Eureka Server** (Service Discovery)
- **API Gateway** (Secure Routing)

## Architecture Diagram and BackEnd SetUp
Document -> https://drive.google.com/file/d/1bVofmszjEd9a4VwNefvi-Xk7eGwyUGlL/view?usp=sharing

Video WalkThrough -> https://drive.google.com/file/d/1p8VIJh4YuLxoul4xtf7bRzrlhrYYO3sM/view?usp=sharing


