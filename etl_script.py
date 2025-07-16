import sqlite3
from datetime import datetime

# Step 1: Connect to churn.db
conn = sqlite3.connect('churn.db')
cursor = conn.cursor()

# Step 2: Create summary table if not exists
cursor.execute('''
    CREATE TABLE IF NOT EXISTS customer_summary (
        customer_id INTEGER PRIMARY KEY,
        name TEXT,
        total_spent REAL,
        last_transaction TEXT,
        churn_risk TEXT
    )
''')

# Step 3: Get all customers
cursor.execute("SELECT customer_id, name FROM customers")
customers = cursor.fetchall()

# Step 4: Loop through each customer to calculate summary
for cust_id, name in customers:
    # Total spent
    cursor.execute("SELECT SUM(amount) FROM transactions WHERE customer_id = ?", (cust_id,))
    total = cursor.fetchone()[0] or 0

    # Last transaction date
    cursor.execute("SELECT MAX(transaction_date) FROM transactions WHERE customer_id = ?", (cust_id,))
    last_txn = cursor.fetchone()[0]

    # Churn Risk = 'High' if no txn in last 6 months
    churn_risk = 'High'
    if last_txn:
        last_date = datetime.strptime(last_txn, "%Y-%m-%d")
        months_diff = (datetime.now().year - last_date.year) * 12 + datetime.now().month - last_date.month
        churn_risk = 'High' if months_diff > 6 else 'Low'

    # Insert or replace into summary
    cursor.execute('''
        INSERT OR REPLACE INTO customer_summary (customer_id, name, total_spent, last_transaction, churn_risk)
        VALUES (?, ?, ?, ?, ?)
    ''', (cust_id, name, total, last_txn, churn_risk))

# Finalize changes
conn.commit()
conn.close()

print("✅ ETL Complete. Summary table created.")
