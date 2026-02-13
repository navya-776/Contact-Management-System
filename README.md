# 📇 Contact Management System (Java Console Application)

## 📌 Project Overview
A menu-driven Contact Management System built using Core Java.  
This application allows users to perform CRUD operations on contacts
with file-based persistence for data storage.

The project demonstrates strong fundamentals in Object-Oriented Programming,
Collections Framework, File Handling, and clean project structure.

---

## 🚀 Features
- Add New Contact
- Update Existing Contact
- Delete Contact
- Search Contact (by name or phone number)
- Display All Contacts
- File-Based Data Persistence
- Input Validation and Exception Handling

---

## 🛠 Technologies Used
- Java (Core Java)
- OOP Principles
- ArrayList (Collections Framework)
- File I/O (FileWriter, BufferedReader)
- Exception Handling

---

## 📂 Project Structure

Contact-Management-System/
│
├── src/
│   ├── model/
│   │   └── Contact.java
│   │
│   ├── service/
│   │   └── ContactService.java
│   │
│   └── Main.java
│
├── data/
│   └── contacts.txt
│
├── README.md
└── .gitignore

---

## ▶ How to Compile and Run

Make sure you are inside the project root directory:

c:\Users\forsu\OneDrive\Desktop\Contact Management-System

### 1️⃣ Compile the Project

javac -encoding UTF-8 -d bin -cp src src/Main.java src/model/Contact.java src/service/ContactService.java

This command:
- Compiles all Java source files
- Stores compiled `.class` files in the `bin` directory
- Preserves package structure

### 2️⃣ Run the Application

java -cp bin Main

This executes the application from the compiled files inside the `bin` folder.

---

## 📖 Concepts Demonstrated
- Object-Oriented Design
- Package Organization
- Separation of Concerns
- Collections Framework Usage
- File Persistence Mechanism
- Exception Handling

---

## 🔮 Future Enhancements
- Add GUI using JavaFX or Swing
- Convert to Web Application (Spring Boot)
- Integrate MySQL Database
- Implement User Authentication

---

## 👩‍💻 Author
Developed as a Core Java project to strengthen backend fundamentals and structured programming practices.
