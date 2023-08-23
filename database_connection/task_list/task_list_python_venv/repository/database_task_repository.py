from database.db_connection import DbConnection
from model.task import Task
from repository.task_repository import TaskRepository


class DatabaseTaskRepository(TaskRepository):
  _insert_query = "INSERT INTO task(title, description, created_at) VALUES('{}', '{}', '{}')"
  _update_query = "UPDATE task SET title = '{}', description = '{}' WHERE id = {}"
  _delete_query = "DELETE FROM task WHERE id = {}"
  _find_all_query = "SELECT * FROM task"
  _find_by_id_query = "SELECT * FROM task WHERE id = {}"

  def __init__(self, db_connection: DbConnection) -> None:
    super().__init__()
    self.db_connection = db_connection

  def insert(self, task: Task):
    query = str.format(self._insert_query, task.title,
                       task.description, task.created_at)
    self.db_connection.execute_query(query)

  def update(self, id: int, task: Task):
    query = str.format(self._update_query, task.title, task.description, id)
    self.db_connection.execute_query(query)

  def delete_by_id(self, id: int):
    query = str.format(self._delete_query, id)
    self.db_connection.execute_query(query)

  def find_by_id(self, id: int) -> Task:
    query = str.format(self._find_by_id_query, id)
    result = self.db_connection.execute_and_fetch_query(query, all=False)
    return Task.from_tuple(result) if result else None

  def find_all(self) -> list[Task]:
    result = self.db_connection.execute_and_fetch_query(self._find_all_query)
    if (result):
      return list(map(lambda tuple: Task.from_tuple(tuple), result))
    return []
