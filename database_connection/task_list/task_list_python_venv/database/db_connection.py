from abc import ABC, abstractmethod


class DbConnection(ABC):
  create_table_query = ""

  def __init__(self) -> None:
    super().__init__()
    self.create_table()

  def create_table(self):
    def operation(_, connection):
      connection.commit()
    self._do_operation(self.create_table_query, operation)

  @abstractmethod
  def get_connection(self):
    pass

  def execute_query(self, query):
    def operation(_, connection):
      connection.commit()
    self._do_operation(query, operation)

  def execute_and_fetch_query(self, query, all=True):
    def operation(cursor, _):
      return cursor.fetchall() if all else cursor.fetchone()
    return self._do_operation(query, operation)

  def _do_operation(self, query, operation):
    try:
      connection = self.get_connection()
      cursor = connection.cursor()
      cursor.execute(query)
      return operation(cursor, connection)
    except Exception as e:
      print("Error on SQL: ", e)
    finally:
      cursor.close()
      connection.close()
