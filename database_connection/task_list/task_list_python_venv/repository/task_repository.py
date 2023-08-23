from model.task import Task


class TaskRepository:
  def insert(self, task: Task):
    pass

  def update(self, id: int, task: Task):
    pass

  def delete_by_id(self, id: int):
    pass

  def find_by_id(self, id: int) -> Task:
    pass

  def find_all(self) -> list[Task]:
    pass
