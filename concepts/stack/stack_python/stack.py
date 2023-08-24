class Stack:
  def __init__(self) -> None:
    self.values = list()

  def push(self, value):
    self.values.append(value)

  def pop(self):
    return self.values.pop()

  def is_empty(self):
    return len(self.values) == 0

  def __str__(self) -> str:
    return ','.join(str(v) for v in self.values)
