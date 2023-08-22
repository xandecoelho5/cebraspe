from database.mysql_connection import MySQLConnection
from database.postgres_connection import PostgresConnection
from database.sqlite_connection import SQLiteConnection
from repository.database_task_repository import DatabaseTaskRepository
from repository.file_task_repository import FileTaskRepository
from service.task_service import TaskService
from utils.file_handler import FileHandler
from view.home_view import HomeView


def main():
  try:
    # task_service = TaskService(FileTaskRepository(FileHandler()))
    db_connection = MySQLConnection()
    task_service = TaskService(DatabaseTaskRepository(db_connection))
    home_view = HomeView(task_service)
    home_view.start()
  except Exception as e:
    raise ("Some error found!! : " + e)


main()
