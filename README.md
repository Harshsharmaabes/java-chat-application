# 💬 Java Chat Application

A simple real-time chat application built in Java using sockets and multithreading.  
The project consists of a **server** that handles multiple clients simultaneously and a **client** application that connects to the server and exchanges messages.

## 🧱 Project Structure
```
ChatApplication/
┣ client/
┃ ┗ Client.java
┣ server/
┃ ┗ Server.java
┣ screenshots/
┃ ┗ chat-demo.png
┗ README.md
```

---

## 🚀 Features
- Real-time text-based messaging between multiple clients
- Multi-threaded server to handle multiple users at once
- Uses TCP sockets for reliable message delivery
- Simple console-based interface for clarity and learning

---

## 🛠️ Technologies Used
- **Java** (Socket Programming, Multithreading)
- **Core Concepts:** I/O Streams, Threads, Exception Handling
- **IDE:** IntelliJ IDEA / VS Code
- **Platform:** JDK 23

---

## 🧩 How to Run
### 1. Compile the files
javac server/Server.java client/Client.java

### 2. Start the server
java server.Server

### 3. Start one or more clients (in new terminals)
java client.Client

📸 Example Output

![alt text](<ScreenShots/Screenshot 2025-11-10 112937.png>)

![alt text](<ScreenShots/Screenshot 2025-11-10 113255.png>)

🧠 Future Improvements

- Add GUI using JavaFX
- Add user authentication
- Implement file sharing between clients
- Add encryption for secure communication
