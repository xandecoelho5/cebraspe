import psycopg2
from database.db_connection import DbConnection


class PostgresConnection(DbConnection):
  create_table_query = """
  CREATE TABLE IF NOT EXISTS task(
    id SERIAL PRIMARY KEY,
    title VARCHAR(512) NOT NULL,
    description VARCHAR(1024) NOT NULL,
    created_at TIMESTAMP
  );
  """

  def get_connection(self):
    return psycopg2.connect("dbname=task_list user=postgres password=postgres")
