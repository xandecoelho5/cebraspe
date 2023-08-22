const TaskDto = require("../dto/TaskDto");
const TaskService = require("../service/TaskService");
const question = require("../utils/ReaderUtil");

class HomeView {
  /**
   * @param {TaskService} taskService
   */
  constructor(taskService) {
    this.taskService = taskService;
  }

  async start() {
    const option = await question(`Selecione uma opçao:
1. Criar nova tarefa
2. Visualizar tarefas
3. Atualizar tarefa
4. Excluir tarefa
0. Sair
`);
    await this.handleOption(Number(option));
  }

  async handleOption(option) {
    switch (option) {
      case 1:
        await this.onCreateNewTask();
        break;
      case 2:
        await this.onShowTasks();
        break;
      case 3:
        await this.onUpdateTask();
        break;
      case 4:
        await this.onDeleteTask();
        break;
      case 0:
        this.onExit();
        break;
      default:
        console.log("Opçao inválida. Selecione outra.");
        break;
    }
    await this.start();
  }

  async onCreateNewTask() {
    const title = await question("\nInforme o título da tarefa: ");
    const description = await question("Informe a descriçao da tarefa: ");

    await this.taskService.addNewTask(new TaskDto(title, description));

    console.log("\nTarefa cadastrada com sucesso!");
  }

  async onShowTasks() {
    const tasks = await this.taskService.retrieveAllTasks();

    if (tasks.length) {
      tasks.sort((a, b) => b.created_at - a.created_at);
      console.log("\nTarefas cadastradas: ");
      tasks.forEach((task) => console.log(`Tarefa ${task.id} -> ${task.toString()}`));
      console.log();
    } else {
      console.log("\nNao há nenhuma tarefa cadastrada.\n");
    }
  }

  async onUpdateTask() {
    const selectedTask = await this.selectATask("alterar");

    console.log("\nInforme os dados para alteraçao, caso nao queira mudar, apenas deixe em branco: ");

    const title = await question("\nInforme o título da tarefa: ");
    const description = await question("Informe a descriçao da tarefa: ");

    await this.taskService.updateTask(selectedTask, new TaskDto(title, description));

    console.log("Tarefa alterada com sucesso!");
  }

  async onDeleteTask() {
    const selectedTask = await this.selectATask("deletar");

    const answer = await question(`\nTem certeza que deseja excluir a tarefa ${selectedTask}? (S/N)`);
    if (answer.toLowerCase() == "s") {
      await this.taskService.deleteTaskById(selectedTask);
      console.log("Tarefa deletada com sucesso!");
    } else if (answer.toLowerCase() == "n") {
      console.log("Tarefa não deletada");
    } else {
      console.log("Opçao inválida.");
    }
  }

  async selectATask(operation) {
    const selectedTaskIndex = Number(await question(`\nQual tarefa deseja ${operation}? (Escolha de acordo com o número de cada tarefa)`));
    await this.taskService.retrieveTaskById(selectedTaskIndex); // throws exception if index is out of bounds
    return selectedTaskIndex;
  }

  onExit() {
    process.exit(1);
  }
}

module.exports = HomeView;
