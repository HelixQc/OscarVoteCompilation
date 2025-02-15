# 🏆 **Oscar Vote Compilation Project** 🎬

Welcome to the **Oscar Vote Compilation Project**! This Java-based CLI application aims to streamline the process of collecting and organizing votes for the prestigious **Academy Awards (Oscars)** using a sophisticated combination of database management and in-memory repository systems.

---

## 🚀 **Project Overview**

The **Oscar Vote Compilation Project** simplifies the management and aggregation of votes for the **Academy Awards**. This system allows users to cast their votes for nominated categories, stores them in a database, and provides an efficient way to tally and display the results. It’s designed to handle voting for multiple categories and nominees, offering an intuitive and secure way to manage large-scale voting events like the Oscars.

---

## 📚 **Features**

- 🗳️ **Vote Casting**: Users can cast their votes for nominees in different categories.
- 📊 **Vote Tallying**: The system automatically compiles and calculates the total number of votes for each nominee.
- 🛠️ **Database Integration**: Votes and nominee data are managed through a combination of database management systems and an in-memory repository.
- 🔐 **Data Security**: Ensures that votes are securely stored and accurately counted.
- ✨ **CLI Interface**: Simple and easy-to-use command-line interface for interacting with the application.
- 🏅 **Result Display**: After voting, users can view the aggregated results by category and nominee.

---

## 🛠️ **Setup Instructions**

### **Prerequisites**

Before you begin, ensure you have the following installed:

- 🖥️ **JDK 8 or higher**: The Java Development Kit is required to compile and run the project.
- 💻 **Database Management System (DBMS)**: You need a DBMS to store the vote data (e.g., **MySQL**, **PostgreSQL**, or any DB of your choice).

### **Clone the Repository**

1. Clone the repository using Git:

    ```bash
    git clone https://github.com/YourUsername/OscarVoteCompilationProject.git
    ```

### **Configure Database**

1. Set up your database using the provided schema or your own preferred database system.

2. Update the connection details in the application. In the `ConnectionDAO`:

    ```properties
    protected static final String DB_HOST = "The Host URL Here";
    protected static final String DB_NAME ="Your dbName";
    protected static final String DB_USER = "Your username";
    protected static final String DB_PASSWORD = "Your Password";
    protected static final String DB_URL = DB_HOST + DB_NAME;
    protected Connection connection ;
    ```
> Note You can also use the In Memory Repositories that are already setup as a singleton!
### **Compile and Run**

1. Open the project in your preferred IDE (e.g., **IntelliJ IDEA**, **Eclipse**).
2. Run the `Main.java` class or use the following Maven/Gradle command to compile and run:

    ```bash
    mvn clean install
    mvn exec:java
    ```

3. Follow the prompts in the CLI to cast your votes, view results, or manage other aspects of the voting process.

---

## 📊 **How It Works**

1. **Vote Casting**:  
   - The user is prompted to select a category and vote for one nominee from a list.
   - Votes are stored in an in-memory repository and also saved to the database.

2. **Vote Tallying**:  
   - The system aggregates the votes in real-time, ensuring that the results are always up to date.
   
3. **Result Display**:  
   - After voting, the user can view the results for each category, including the top nominees and total vote counts.

---

## ⚠️ **Disclaimer**

This project was developed for educational purposes and is **not** optimized for production or large-scale voting events. It is designed to showcase basic principles of vote aggregation, database management, and in-memory data handling. The system is not intended for handling real-world voting events, especially those requiring high security or scalability.

---

## 🛡️ **License**

This project is licensed under the **MIT License**. By using, modifying, or distributing the code, you agree to the following terms:

1. 🏷️ **Ownership**: All intellectual property rights, including but not limited to the source code, design, and architecture, are owned by **Your Name**.  
2. ❌ **Restrictions**:  
   - You are free to use, copy, modify, and distribute the software for personal, educational, or commercial purposes, as long as the copyright notice is included.  
   - **Your Name** is not responsible for any issues or damages that arise from the use or misuse of this system.  
3. 🔐 **Usage**:  
   - Feel free to use this software for learning or personal projects—just make sure to respect the terms of the license.

---

## 💬 **Questions or Feedback?**

If you have any questions, suggestions, or feedback, feel free to reach out.  
Enjoy working with and learning from this Oscar Vote Compilation system! 🎬

---

### 🥳 **Thank you for checking out this project!**
