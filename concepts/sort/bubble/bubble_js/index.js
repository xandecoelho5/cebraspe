const array = [60, 20, 54, 23, 12, 43, 23, 55];

const sortArr = (arr) => {
  for (let i = arr.length - 1, temp; i >= 0; --i) {
    for (let j = i - 1; j >= 0; --j) {
      if (arr[i] < arr[j]) {
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
  }
};

console.log(array);
sortArr(array);
console.log(array);
