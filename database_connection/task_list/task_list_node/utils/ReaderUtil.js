const readline = require("readline");
const util = require("util");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const question = util.promisify(rl.question).bind(rl);

module.exports = question;
