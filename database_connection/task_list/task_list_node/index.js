const MySqlConnection = require("./database/MySqlConnection");
const PostgresConnection = require("./database/PostgresConnection");
const SQLiteConnection = require("./database/SQLiteConnection");
const DatabaseTaskRepository = require("./repository/DatabaseTaskRepository");
const FileTaskRepository = require("./repository/FileTaskRepository");
const TaskService = require("./service/TaskService");
const HomeView = require("./view/HomeView");

const main = async () => {
  const connection = new SQLiteConnection();
  try {
    const taskService = new TaskService(new DatabaseTaskRepository(connection));
    // const taskService = new TaskService(new FileTaskRepository());
    const homeView = new HomeView(taskService);
    await homeView.start();
  } finally {
    console.log("doaskdosakod");
    await connection.close();
  }
};

main();
