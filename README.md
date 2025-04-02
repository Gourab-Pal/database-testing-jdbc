# 🚀 MySQL Database Automation with Selenium & TestNG

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF9800?style=for-the-badge&logo=testng&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)

A **Test Automation Framework** for interacting with a **MySQL Database** using **Java, TestNG, and JDBC**. This project automates CRUD operations on a database and verifies test cases using assertions. 🚀

## 📌 Features
✅ Establishes a connection to a MySQL database.
✅ Fetches, inserts, and validates data dynamically.
✅ Implements **TestNG** for structured test execution.
✅ Uses **JDBC** for seamless SQL query execution.
✅ Properly handles database connections & resources.

---

## 🛠️ Setup & Installation
### **Prerequisites**
Ensure you have the following installed on your system:
- ☕ [Java JDK 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- 🐬 [MySQL Server](https://dev.mysql.com/downloads/installer/)
- 📦 [Gradle](https://maven.apache.org/download.cgi) (Optional for dependency management)
- 💻 [VS Code](https://code.visualstudio.com/download) (Recommended IDE)

### **1️⃣ Clone the Repository**
```sh
  git clone https://github.com/Gourab-Pal/database-testing-jdbc.git
  cd database-testing-jdbc
```

### **2️⃣ Configure MySQL Database**
1. Start your MySQL server from MySQL workbench.
2. Create a database named **`automationtesting`**.
3. Create a `users` table with the following table schema:

```sql
CREATE TABLE users (
    userid INT PRIMARY KEY,
    username VARCHAR(255),
    city VARCHAR(100),
    salary INT
);
```

4. Add some sample data:
```sql
INSERT INTO users VALUES (1001, 'John Doe', 'New York', 5000);
INSERT INTO users VALUES (1002, 'Jane Smith', 'Los Angeles', 4500);
```

### **3️⃣ Update Database Credentials**
Modify the `Template.java` file with your MySQL credentials:
```java
String userName = "put username";
String password = "put password";
String dbUrl = "jdbc:mysql://localhost:3306/automationtesting?useSSL=true";
```

### **4️⃣ Run the Test Cases**
Execute the test cases using **TestNG**:
```sh
./gradlew test
```

---

## 🧪 Test Cases Explained
### **✔ Test Case 1 - Verify Rows Exist**
```java
@Test(description = "Verify rows are present in the table")
```
- Checks if there are records in the `users` table.
- Uses **COUNT(*)** to validate data presence.

### **➕ Test Case 2 - Insert and Validate Row Count**
```java
@Test(description = "Add one row and check if the number of row count increases")
```
- Inserts a new user into the table.
- Validates if the record count increased by **1**.

### **📊 Test Case 3 - Count Users with Salary > 1000**
```java
@Test(description = "How many people have salary more than 1000?")
```
- Fetches the count of users with salary above **1000**.
- Displays the result in the console.

---

## 📂 Project Structure
```
📦 mysql-automation-testng
 ┣ 📜 src/main/java/demo/Template.java  # Main test class
 ┣ 📜 build.gradle                       # Gradle dependencies
 ┣ 📜 testng.xml                         # TestNG configuration
 ┗ 📜 README.md                          # Documentation
```

---

## 📌 Dependencies
This project uses **Gradle** for dependency management. Ensure the following dependencies exist in your `build.gradle`:

```sh
dependencies {

    testImplementation 'org.testng:testng:6.9.10'

    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
    implementation 'org.seleniumhq.selenium:selenium-java:4.21.0'
    implementation 'org.apache.poi:poi-ooxml:4.1.2'

    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation("mysql:mysql-connector-java:8.0.33")

}
```

---

## 📜 License
This project is free to use with a follow.

## 🏆 Contributing
Feel free to contribute by **creating pull requests** or **reporting issues**.

## ⭐ Show Your Support
If you like this project, give it a ⭐ on [GitHub](https://github.com/Gourab-Pal/database-testing-jdbc)! 🎉

