const fs = require("fs");

const filePath = "resources/task_list.json";

const writeObjectToFile = (jsonData) => {
  const json = JSON.stringify(jsonData);
  fs.writeFileSync(filePath, json, "utf-8", (err) => {
    if (err) {
      console.error("An error occurred while writing JSON Object to File." + err);
      return;
    }
    console.log("JSON file has been saved");
  });
};

const readFromFile = () => {
  if (fs.existsSync(filePath)) {
    try {
      const json = fs.readFileSync(filePath, "utf-8");
      return JSON.parse(json);
    } catch (err) {
      console.log(err);
    }
  }
  return [];
};

module.exports = { writeObjectToFile, readFromFile };
