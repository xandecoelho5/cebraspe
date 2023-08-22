import mysql.connector
from database.db_connection import DbConnection


class MySQLConnection(DbConnection):
  create_table_query = """
  CREATE TABLE IF NOT EXISTS task(
    id INTEGER NOT NULL AUTO_INCREMENT,
    title VARCHAR(512) NOT NULL,
    description VARCHAR(1024) NOT NULL,
    created_at DATETIME,
    PRIMARY KEY (id)
  );
  """

  def get_connection(self):
    return mysql.connector.connect(
        host='localhost', database='task_list', user='root', password='root')
