const Stack = require("./Stack");

const myStack = new Stack();

myStack.push(1);
myStack.push(6);
myStack.push(2);
myStack.push(3);
console.log(myStack.toString());
console.log(myStack.pop());
console.log(myStack.pop());
console.log(myStack.pop());
console.log(myStack.pop());
