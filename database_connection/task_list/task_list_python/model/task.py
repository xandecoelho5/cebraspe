from datetime import datetime
from dto.task_dto import TaskDto


class Task:
  def __init__(self, id: int, title: str, description: str, created_at: datetime) -> None:
    self.id = id
    self.title = title
    self.description = description
    self.created_at = created_at

  @staticmethod
  def from_dto(task_dto: TaskDto):
    return Task(0, task_dto.title, task_dto.description, datetime.now())

  @staticmethod
  def from_tuple(tuple: ()):
    created_at = tuple[3] if type(
      tuple[3]) != str else datetime.strptime(tuple[3].split(".")[0], "%Y-%m-%d %H:%M:%S")
    return Task(tuple[0], tuple[1], tuple[2], created_at)

  def update_data(self, task_dto: TaskDto):
    self.title = task_dto.title
    self.description = task_dto.description

  def __str__(self) -> str:
    created_date = self.created_at.strftime("%d/%m/%Y, %H:%M:%S")
    return f"Título: {self.title}, Descriçao: {self.description}, Data de criaçao: {created_date}"
