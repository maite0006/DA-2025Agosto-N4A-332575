# Toll System Prototype (Java + Spring Boot)

## 📌 Description
Prototype implementation of a toll management system, focused on modeling domain logic, applying design patterns, and structuring a maintainable architecture.

The system manages toll booths, vehicles, owners, tariffs, and transit operations, including discounts, user states, and notifications.

---

## 🎯 Objective
To design and implement a system with strong domain modeling and correct application of object-oriented principles, design patterns, and MVC architecture.

---

## 🛠️ Technologies
- Java
- Spring Boot
- HTML, CSS, JavaScript (web interface)

---

## 🏗️ Architecture & Design

The system is structured following the **MVC (Model-View-Controller)** pattern:

- **Model**: Domain entities and business rules  
- **View**: Web interface (HTML + JS)  
- **Controller**: Handles user actions and system coordination  

### Design principles and patterns applied:
- **Facade Pattern** → centralized access to system operations  
- **GRASP (Expert Principle)** → responsibilities assigned to appropriate classes  
- **Polymorphism** → behavior variation based on vehicle type, user state, and discounts  
- **Observer / Event handling** → notifications and system updates  
- **Exception handling** → controlled error flows  
- **Low coupling & high cohesion**  

---

## ⚙️ Features

### 🚗 Vehicle & Owner Management
- Registration of vehicle owners and vehicles  
- Unique ownership constraint per vehicle  
- Owner states (Enabled, Disabled, Suspended, Penalized)  

### 🛣️ Toll System
- Management of toll booths with associated tariffs  
- Transit registration with automatic cost calculation  
- Balance validation before processing  

### 💸 Discounts & Rules
- Exemptions (free transit)  
- Frequent user discounts (based on daily usage)  
- Worker discounts (weekday-based)  
- One discount per toll booth per owner  

### 🔔 Notifications
- Automatic notifications on transit events  
- Low balance alerts  
- State change notifications  

### 🧪 Admin Features
- Transit emulation  
- Discount assignment  
- Owner state management  

---

## 📈 Notes

This project emphasizes:
- Domain modeling over UI complexity  
- Correct application of design patterns  
- Separation of concerns through MVC  
- Implementation of real-world business rules  

It was developed as part of an academic assignment focused on software design quality, not only functional correctness.
