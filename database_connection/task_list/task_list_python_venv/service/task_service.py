from dto.task_dto import TaskDto
from model.task import Task
from repository.task_repository import TaskRepository


class TaskService:
  def __init__(self, task_repository: TaskRepository) -> None:
    self.task_repository = task_repository

  def add_new_task(self, task_dto: TaskDto):
    task = Task.from_dto(task_dto)
    self.task_repository.insert(task)

  def update_task(self, id: int, task_dto: TaskDto):
    task = self.retrieve_task_by_id(id)
    task.update_data(task_dto)
    self.task_repository.update(id, task)

  def delete_task_by_id(self, id: int):
    self.retrieve_task_by_id(id)
    self.task_repository.delete_by_id(id)

  def retrieve_task_by_id(self, id: int) -> Task:
    return self.task_repository.find_by_id(id)

  def retrieve_all_tasks(self) -> list[Task]:
    return self.task_repository.find_all()
