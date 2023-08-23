from model.task import Task
from dto.task_dto import TaskDto
from service.task_service import TaskService
from repository.database_task_repository import DatabaseTaskRepository
from database.mock_sqlite_connection import MockSQLiteConnection

task_repository = DatabaseTaskRepository(MockSQLiteConnection())
task_service = TaskService(task_repository)
task_dto = TaskDto('teste', 'desc-teste')


def clean_data():
  tasks = task_repository.find_all()
  for task in tasks:
    task_repository.delete_by_id(task.id)


def test_add_new_task_inserts_task_to_repository():
  task = Task.from_dto(task_dto)

  task_service.add_new_task(task_dto)

  saved_task = task_service.retrieve_all_tasks()[0]

  assert saved_task.title == task.title
  assert saved_task.description == task.description
  clean_data()


def test_delete_task_by_id_deletes_a_task_from_repository():
  task_service.add_new_task(task_dto)

  saved_task = task_service.retrieve_all_tasks()[0]

  task_service.delete_task_by_id(saved_task.id)

  assert len(task_service.retrieve_all_tasks()) == 0
  clean_data()


def test_retrieve_all_tasks_returns_all_tasks():
  task_service.add_new_task(TaskDto('super', 'maneiro'))
  task_service.add_new_task(TaskDto('tpo', 'zera'))
  task_service.add_new_task(TaskDto('skok', 'asdkosa'))

  assert len(task_service.retrieve_all_tasks()) == 3
  clean_data()


def test_retrieve_task_by_id_returns_a_task():
  task_service.add_new_task(task_dto)

  saved_task = task_service.retrieve_all_tasks()[0]

  task_by_id = task_service.retrieve_task_by_id(saved_task.id)

  assert task_dto.title == task_by_id.title
  assert task_dto.description == task_by_id.description
  clean_data()


def test_update_task_updates_a_task_from_repository():
  task_service.add_new_task(task_dto)

  saved_task = task_service.retrieve_all_tasks()[0]

  updated_dto = TaskDto('foto', 'suokkss')

  task_service.update_task(saved_task.id, updated_dto)

  saved_task = task_service.retrieve_all_tasks()[0]

  assert updated_dto.title == saved_task.title
  assert updated_dto.description == saved_task.description
  clean_data()
