📊 Customer Churn & Profitability Analysis

An end-to-end project that integrates SQL, Python, Java, and Power BI to simulate a real-world customer churn analytics system. It analyzes transactional behavior, churn risk, and customer value using a SQLite database, automates insights via Python, exposes structured access via Java, and visualizes it through Power BI dashboards.

🧰 Tech Stack

Database: SQLite

Data Processing: Python (ETL, churn flagging)

Backend Access: Java (JDBC + CLI menu)

Reporting & Dashboards: Power BI

📁 Project Structure

Customer_Churn_Project/
├── churn.db                       # SQLite database file
├── schema.sql                    # SQL schema for customers & transactions
├── dummy_data.sql                # Sample data insertions
├── etl_script.py                 # Python ETL + churn logic
├── java/
│   ├── Main.java                 # Java CLI interface
│   ├── DBConnection.java         # JDBC SQLite connector
│   └── sqlite-jdbc-3.42.0.0.jar  # SQLite JDBC driver
├── powerbi/
│   ├── churn_dashboard.pbix      # Power BI dashboard file
│   ├── dashboard1.png            # Screenshots of visuals
│   └── kpi_cards.png             # More visuals
└── README.md

🔄 Data Flow Overview

schema.sql + dummy_data.sql create a simple customer and transaction system.

etl_script.py processes:

Total spend per customer

Last transaction date

Flags churn risk (High if no recent activity)

Outputs customer_summary table

Main.java CLI lets user:

View all summaries

Get churn info by customer ID

Power BI connects to churn.db via ODBC and visualizes churn, spend, risk, and KPIs

📊 Power BI Dashboard Includes

✅ KPI Cards (Total Customers, Average Spend, Churn %)

🥧 Pie chart of High vs Low Risk

📋 Customer-level churn table

📍 Region-wise trends (if extended)

▶️ How to Run Locally

1. Set up the Database

sqlite3 churn.db < schema.sql
sqlite3 churn.db < dummy_data.sql

2. Run Python ETL

python etl_script.py

3. Compile & Run Java CLI

cd java
javac -cp ".;sqlite-jdbc-3.42.0.0.jar" Main.java DBConnection.java
java -cp ".;sqlite-jdbc-3.42.0.0.jar" Main

4. View Dashboard in Power BI

Open powerbi/churn_dashboard.pbix

Interact with slicers, KPIs, and visuals

📌 Author

Vedantam Venkata Sri BharathiGitHub: yourusernameLinkedIn: linkedin.com/in/venkata-sri-bharathi-v-713b16214

⭐ Suggestions to Improve

Add time-series forecasting

Connect Java to REST API (Spring Boot)

Use real datasets (Telco, Bank)

Deploy Power BI to online workspace

This project demonstrates strong data flow handling across multiple technologies and is ideal for roles in SQL Development, BI Reporting, or Data Engineering.

Happy analyzing! 🚀

