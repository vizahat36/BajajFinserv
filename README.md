# BajajFinserv — SQL Webhook Submission

This repository contains a small **Spring Boot** application used to:

- Generate a webhook  
- Obtain an access token  
- Submit a final SQL query to the assessment endpoint  

It was prepared as the submission for the **Bajaj Finserv SQL Assignment (Question 2)** assigned using **Reg. No: XXXXXXXX6068**.

---

## 📁 Repository Contents

| Path / File | Description |
|--------------|-------------|
| `src/` | Source code |
| `target/` | Build artifacts (generated after Maven build) |
| `run-output.txt` | Raw run output (⚠️ contains sensitive access token) |
| `run-output-sanitized.txt` | Sanitized output (token redacted) |

---

## 🧾 Quick Summary

- **RegNo used:** `XXXXXXXX6068`  
- **Assigned question:** Question 2 (Even → last two digits = 68)

### ✅ Final SQL Submitted

```sql
SELECT e.emp_id, e.first_name, e.last_name, d.department_name,
       (SELECT COUNT(*) FROM employee e2
        WHERE e2.department = e.department AND e2.dob > e.dob) AS younger_employees_count
FROM employee e
JOIN department d ON e.department = d.department_id
ORDER BY e.emp_id DESC;


🔧 What Was Changed
File	Description
src/main/java/com/bajaj/bajajfinserv/util/SQLSolver.java	Added getEvenQuestionFinalQuery() returning the final SQL for Question 2.
src/main/java/com/bajaj/bajajfinserv/service/WebhookService.java	Wired SQLSolver output as the finalQuery posted to the webhook URL.
src/test/java/com/bajaj/bajajfinserv/OneTimeTest.java	Added one-time test to execute the full flow and verify submission.
⚙️ Build and Run (PowerShell)
1️⃣ Build the Project
Set-Location -Path 'E:\Bajaj-assigenment\bajajfinserv'
.\mvnw.cmd clean package


After a successful build, the JAR will be at:

target\bajajfinserv-0.0.1-SNAPSHOT.jar

2️⃣ Run the Standalone App
java -jar .\target\bajajfinserv-0.0.1-SNAPSHOT.jar

3️⃣ Run the One-Shot Test
.\mvnw.cmd -Dtest=OneTimeTest test

🖥️ Expected Console Output

The application prints:

Webhook URL

Access Token (JWT)

Submission Response (JSON success message)

Example (Sanitized)
Webhook URL: https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
Access Token: <REDACTED>
Submission response: {"success":true,"message":"Webhook processed successfully"}

🧹 Sanitize Logs (Redact Token)

If you captured the raw output as run-output.txt, run this PowerShell command to sanitize it:

(Get-Content .\run-output.txt) -replace 'Access Token: .*', 'Access Token: <REDACTED>' | Set-Content .\

🧠 How the Challenge Was Solved
Step 1 — Identify Assigned Question

RegNo U25UV23T006068 → last two digits are even → Question 2

Step 2 — Analyze Question and Schema

Tables:

employee(emp_id, first_name, last_name, department, dob)

department(department_id, department_name)

Requirement: For each employee, count how many employees in the same department are younger.

Step 3 — Plan SQL Logic

Use a correlated subquery:

For each employee e, count employees e2 where
e2.department = e.department AND e2.dob > e.dob.

Step 4 — Implement in Code

The SQL was added in:

SQLSolver.getEvenQuestionFinalQuery()

Step 5 — Submission Flow

Request webhook generator endpoint → get Webhook URL + Access Token

Fetch SQL from SQLSolver

POST { "finalQuery": "<SQL>" } to the webhook URL with Bearer Token

Receive JSON response confirming successful submission

📤 Expected Output Example
Webhook URL: https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
Access Token: <REDACTED>
Submission response: {"success":true,"message":"Webhook processed successfully"}

📦 Submission Guidance

When submitting, include:

target\bajajfinserv-0.0.1-SNAPSHOT.jar

run-output-sanitized.txt


💡 Optional Improvements

Mask tokens dynamically:

String masked = accessToken.replaceAll("(.{8}).+(.{8})", "$1...$2");
System.out.println("Access Token: " + masked);


Possible Enhancements:

Add CI/CD to build and sanitize automatically

Add RESULTS.md to demonstrate SQL output with mock data

Add SQL validation unit tests

🧩 Project Info
Property	Value
Framework	Spring Boot
Build Tool	Maven
Language	Java
Assignment	Bajaj Finserv SQL (Even Question → Q2)
RegNo	  XXXXXXXX6068

👤 Author

Mohammed Vijahath (Viz)
🎓 B.Tech in Artificial Intelligence and Machine Learning — UVCE
📧 mohammedvijahath@gmail.com
🌐 https://github.com/vizahat36


---

✅ You can now:
- Save this file as **`README.md`** in your project root folder.  
- Replace `example@example.com` with your real email if you’d like.  

Would you like me to add a **GitHub-style badge header** (for Java, Spring Boot, Maven, etc.) at the top for a more polished profile look?
