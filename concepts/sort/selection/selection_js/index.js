const arr = [60, 20, 54, 23, 12, 43, 23, 1];

const selectionSort = (array) => {
  for (let i = 0; i < array.length; ++i) {
    let minIndex = i;

    for (let j = i + 1; j < array.length; ++j) {
      if (array[j] < array[minIndex]) {
        minIndex = j;
      }
    }

    let temp = array[i];
    array[i] = array[minIndex];
    array[minIndex] = temp;
  }
};

selectionSort(arr);
console.log(arr);
