from model.task import Task
from repository.task_repository import TaskRepository
from utils.file_handler import FileHandler


class FileTaskRepository(TaskRepository):
  def __init__(self, file_handler: FileHandler) -> None:
    super().__init__()
    self.file_handler = file_handler

  def insert(self, task: Task):
    tasks = self.find_all()
    task.id = 1 if not tasks else tasks[len(tasks) - 1].id + 1
    tasks.append(task)
    self.file_handler.write_to_file(tasks)

  def update(self, id: int, task: Task):
    tasks = self.find_all()
    tasks[id - 1] = task
    self.file_handler.write_to_file(tasks)

  def delete_by_id(self, id: int):
    tasks = self.find_all()
    tasks.remove(tasks[id - 1])
    self.file_handler.write_to_file(tasks)

  def find_by_id(self, id: int) -> Task:
    tasks = self.find_all()
    return tasks[id - 1]

  def find_all(self) -> list[Task]:
    return self.file_handler.read_from_file()
