const TaskService = require("../../service/TaskService");
const DatabaseTaskRepository = require("../../repository/DatabaseTaskRepository");
const MockSQLiteConnection = require("../mock/MockSQLiteConnection");
const TaskDto = require("../../dto/TaskDto");
const Task = require("../../model/Task");

describe("TaskService", () => {
  let taskService;
  let taskRepository;
  const taskDto = new TaskDto("teste", "desc-teste");

  beforeAll(async () => {
    const connection = new MockSQLiteConnection();
    await connection.init();
    taskRepository = new DatabaseTaskRepository(connection);
    taskService = new TaskService(taskRepository);
  });

  afterEach(async () => {
    const tasks = await taskRepository.findAll();
    for (const task of tasks) {
      await taskRepository.deleteById(task.id);
    }
  });

  test("addNewTask inserts task to repository", async () => {
    const task = Task.fromDto(taskDto);

    await taskService.addNewTask(taskDto);

    const tasks = await taskRepository.findAll();
    const savedTask = tasks[0];

    expect(tasks.length).toBe(1);
    expect(savedTask.title).toBe(task.title);
    expect(savedTask.description).toBe(task.description);
  });

  test("deleteTaskById deletes a Task by its Id", async () => {
    await taskService.addNewTask(taskDto);

    let tasks = await taskRepository.findAll();

    await taskService.deleteTaskById(tasks[0].id);

    tasks = await taskRepository.findAll();

    expect(tasks.length).toBe(0);
  });

  test("retrieveAllTasks should return All Tasks", async () => {
    await taskService.addNewTask(new TaskDto("dsaodk", "doqkeowqke"));
    await taskService.addNewTask(new TaskDto("ekowqkeowqk", "ckoxkcoak"));
    await taskService.addNewTask(new TaskDto("dpasdlpaskl", "fkasdojska"));

    const tasks = await taskRepository.findAll();

    expect(tasks.length).toBe(3);
  });

  test("retrieveTaskById should return All Tasks", async () => {
    await taskService.addNewTask(taskDto);

    const tasks = await taskRepository.findAll();

    const taskById = await taskService.retrieveTaskById(tasks[0].id);

    expect(taskById.title).toBe(taskDto.title);
    expect(taskById.description).toBe(taskDto.description);
  });

  test("updateTask updates a task in repository", async () => {
    await taskService.addNewTask(taskDto);

    let tasks = await taskRepository.findAll();
    let savedTask = tasks[0];

    const updatedDto = new TaskDto("colera", "do dragao");

    await taskService.updateTask(savedTask.id, updatedDto);

    tasks = await taskRepository.findAll();
    savedTask = tasks[0];

    expect(savedTask.title).toBe(updatedDto.title);
    expect(savedTask.description).toBe(updatedDto.description);
  });
});
