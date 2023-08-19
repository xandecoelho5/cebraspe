const readline = require("readline");
const util = require("util");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const question = util.promisify(rl.question).bind(rl);

const calculateAverageFromArray = (numbers) => {
  if (!numbers) return 0;

  const sum = numbers.reduce((acc, curr) => acc + curr, 0);
  return sum / numbers.length;
};

const main = async () => {
  const numbersForCalculate = [];
  const quantityOfNumbers = await question("Quantos números deseja inserir? ");

  for (let i = 0; i < quantityOfNumbers; i++) {
    const value = Number(await question(`Insira o número ${i + 1}: `));
    numbersForCalculate.push(value);
  }

  const average = calculateAverageFromArray(numbersForCalculate);
  console.log(`A média dos números inseridos é: ${average.toFixed(2)}`);

  rl.close();
};

main();

module.exports = calculateAverageFromArray;
