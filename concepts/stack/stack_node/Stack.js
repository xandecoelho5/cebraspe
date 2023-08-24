class Stack {
  constructor() {
    this.list = [];
  }

  push(val) {
    this.list.push(val);
  }

  pop() {
    return this.list.pop();
  }

  isEmpty() {
    return this.list.length == 0;
  }

  toString() {
    return this.list.join(",");
  }
}

module.exports = Stack;
