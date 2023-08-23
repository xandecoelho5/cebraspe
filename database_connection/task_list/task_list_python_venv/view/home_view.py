from dto.task_dto import TaskDto
from service.task_service import TaskService


class HomeView:
  def __init__(self, task_service: TaskService) -> None:
    self.task_service = task_service

  def start(self):
    option = int(input("""
Selecione uma opçao:
1. Criar nova tarefa
2. Visualizar tarefas
3. Atualizar tarefa
4. Excluir tarefa
0. Sair
"""))
    self.handle_option(option)

  def handle_option(self, option: int):
    match option:
      case 1: self._on_create_new_task()
      case 2: self._on_show_tasks()
      case 3: self._on_update_task()
      case 4: self._on_delete_task()
      case 0: self._on_exit()
      case _: print("Opçao inválida. Selecione outra.")
    self.start()

  def _on_create_new_task(self):
    title = input("\nInforme o título da tarefa: ")
    description = input("Informe a descriçao da tarefa: ")
    self.task_service.add_new_task(TaskDto(title, description))
    print("\nTarefa cadastrada com sucesso!")

  def _on_show_tasks(self):
    tasks = self.task_service.retrieve_all_tasks()

    if (tasks):
      tasks = sorted(tasks, key=lambda task: task.created_at, reverse=True)
      print("\nTarefas cadastradas: ")

      for task in tasks:
        print(str.format("Tarefa {} -> {}", task.id, task))
    else:
      print("\nNao há nenhuma tarefa cadastrada.")

  def _on_update_task(self):
    selected_task = self.select_a_task("alterar")

    print("\nInforme os dados para alteraçao, caso nao queira mudar, apenas deixe em branco: ")

    title = input("\nInforme o título da tarefa: ")
    description = input("Informe a descriçao da tarefa: ")
    self.task_service.update_task(selected_task, TaskDto(title, description))

    print("Tarefa alterada com sucesso!")

  def _on_delete_task(self):
    selected_task = self.select_a_task("deletar")

    answer = input(
      f"\nTem certeza que deseja excluir a tarefa {selected_task}? (S/N)")
    if (answer.lower() == "s"):
      self.task_service.delete_task_by_id(selected_task)
      print("Tarefa deletada com sucesso!")
    elif (answer.lower() == "n"):
      print("Tarefa não deletada")
    else:
      print("Opçao inválida.")

  def select_a_task(self, operation: str) -> int:
    selected_index = int(input(
      f"\nQual tarefa deseja {operation}? (Escolha de acordo com o número de cada tarefa)"))
    # throws exception if index is out of bounds
    self.task_service.retrieve_task_by_id(selected_index)
    return selected_index

  def _on_exit(self):
    print("Obrigado pela preferência.")
    exit()
