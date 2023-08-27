const isPalindrome = function (x) {
  if (x >= 0) {
    let aux = x,
      reverse = 0;
    while (aux != 0) {
      let remainder = aux % 10;
      reverse = reverse * 10 + remainder;
      aux = Math.floor(aux / 10);
    }
    return x === reverse;
  }
  return false;
};

isPalindrome(10);
