from io import StringIO
import pandas as pd
import sqlite3
import time

BASE_INSERT_QUERY = "INSERT INTO invoice (client_id, product_category, product_sku, date, quantity, billing_amount) VALUES "
CREATE_TABLE_QUERY = """
  CREATE TABLE IF NOT EXISTS invoice(
    client_id INTEGER,
    product_c ategory TEXT,
    product_sku TEXT,
    date DATE,
    quantity INTEGER,
    billing_amount REAL
  );
"""


def execute_query(query):
  try:
    con = sqlite3.connect("excel_import.db")
    cur = con.cursor()
    cur.execute(query)
  except Exception as e:
    print(e)
  finally:
    con.commit()
    con.close()


def fetch_all(query):
  try:
    con = sqlite3.connect("excel_import.db")
    cur = con.cursor()
    res = cur.execute(query)
    return res.fetchall()
  finally:
    con.commit()
    con.close()


def replace_last_char(old_string, char):
  index = len(old_string) - 1
  return old_string[:index] + char + old_string[index + 1:]


def insert_on_db():
  global count, sentence
  query = replace_last_char(sentence.getvalue(), ";")
  execute_query(query)
  sentence = StringIO()
  count = 0


def read_df_and_insert_on_db():
  start_time = time.perf_counter()
  global count

  def build_query_from_row(clientId, productCategory, productSku, date, quantity, billingAmount):
    global count

    if (count == 0):
      sentence.write(BASE_INSERT_QUERY)

    sentence.write(" ({}, '{}', '{}', '{}', {}, {}),".format(
        clientId, productCategory, productSku, date, quantity, billingAmount))
    count += 1

    if (count % 2500 == 0):
      insert_on_db()

  for row in zip(df['Código Cliente'], df['Categoria do Produto'], df['Sku/Produto'], df['Data'], df['Quantidade'], df['Valor de Faturamento']):
    build_query_from_row(row[0], row[1], row[2], row[3].date(), row[4], row[5])

  if (count > 0):
    insert_on_db()

  end_time = time.perf_counter()
  print("%.2fms" % ((end_time - start_time) * 1000))


count = 0
sentence = StringIO()
execute_query(CREATE_TABLE_QUERY)

df = pd.read_excel(
    'C:\\NewProjects\\cebraspe\\excel_import\\dummy_data.xlsx')
df.reset_index()

need_to_insert = bool(fetch_all("SELECT COUNT(*) > 0 FROM invoice")[0])

if (need_to_insert):
  read_df_and_insert_on_db()
