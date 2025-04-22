# 🔐 CrypticShell

CrypticShell is a secure file management tool built in **Java** with **Maven**, utilizing **MySQL** for persistent storage and custom **email-based authentication**. It allows users to hide, view, or delete files from their local system with a secure, user-specific login system.

---

## ✨ Features

- 🔑 **Login / Signup via Email OTP Verification**  
- 📁 **Hide/Delete Custom Files** from local storage  
- 👁️ **Show Hidden Files** associated with the logged-in user  
- 🔓 **Decrypt Files** back to original form  
- 💽 **Persistent Storage in MySQL Database**  
- 🛡️ **Custom Authentication Mechanism**  
- 🖼️ **Clean Console UI with Step-by-Step Screenshots (Coming Soon)**  

---

## 📌 Step-by-Step Flow

1. **Welcome Page**  
   Options: `Login | Register | Exit`  
   Includes email OTP verification for secure access

2. **User Dashboard**  
   - View all hidden files  
   - Encrypt/hide more files  
   - Decrypt files  
   - Exit  

---

## 🏗️ Tech Stack

- **Java** (Core + OOP)
- **Maven** for dependency management
- **MySQL** for user and file metadata storage
- **JavaMail API** for sending OTPs
- **Java I/O & File Handling**

---

## 🔮 Future Scope

- 🔐 Store hidden files as **Base64-encoded strings** in DB  
- 🔒 Add AES encryption for even stronger file security  
- 🌐 GUI-based version with JavaFX or a web interface  

---

## 📷 Screenshots

1. Welcome Page :
   
   ![image](https://github.com/user-attachments/assets/4b6be9e8-b507-42ad-bd41-aedafc927670)

3. User Register :
   
   Initial Database before registration : 
      ![image](https://github.com/user-attachments/assets/13ebd333-5635-422a-b90f-db6384204a5c)

   Post User Registration : 
      ![image](https://github.com/user-attachments/assets/2a0876f8-1b47-457d-b45d-af834a785ff3)
   
      ![image](https://github.com/user-attachments/assets/6e62b514-1de2-4f02-abfc-d70df2331c50)

5. User Login :
   
   Valid User Check-
      ![image](https://github.com/user-attachments/assets/240760e0-6ce2-4813-b595-7df237116151)

7. User Page (post login) :
   
   ![image](https://github.com/user-attachments/assets/01536de2-44d6-4338-8828-000a1c53b67c)

9. Encrypt file :
    
   Before adding files :
      ![image](https://github.com/user-attachments/assets/9508d6ad-ca4e-4b5c-b1e2-c15358514853)

   After adding files :
      ![image](https://github.com/user-attachments/assets/cc4f88f3-8bd0-4328-b094-4d56bb859a0b)

      ![image](https://github.com/user-attachments/assets/d5436980-f9a4-4f56-9614-83c691be2b94)

      ![image](https://github.com/user-attachments/assets/33f167d0-7994-423d-b429-3fa7333f3af7)

11. Decrpyt File :

   ![image](https://github.com/user-attachments/assets/0b116159-be58-4595-abb9-3fa6474b4546)
   
   ![image](https://github.com/user-attachments/assets/57f79b13-a073-4fdc-9866-def1644779c4)


---

## 🚀 Getting Started

1. Clone the repo:
   ```bash
   git clone https://github.com/Sam23599/CrypticShell.git
   cd crypticshell
   ```

2. Set up `MySQL` database and update your credentials in the config file

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

4. Run the project:
   ```bash
   java -jar target/crypticshell.jar
   ```

---
