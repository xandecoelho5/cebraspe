import sqlite3
from database.db_connection import DbConnection


class SQLiteConnection(DbConnection):
  create_table_query = """
  CREATE TABLE IF NOT EXISTS task(
    id INTEGER PRIMARY_KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    created_at DATETIME
  );
  """

  def get_connection(self):
    return sqlite3.connect("resources/task_list.db")
