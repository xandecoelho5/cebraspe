const calculateAverageFromArray = require("./index");

test("calculates average of these 4 numbers: 10, 15, 20 and 25", () => {
  expect(calculateAverageFromArray([10, 15, 20, 25])).toBe(17.5);
});
