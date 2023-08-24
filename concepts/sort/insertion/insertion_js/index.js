const arr = [60, 20, 54, 23, 12, 43, 23, 55];

for (let i = 1, j, key; i < arr.length; ++i) {
  key = arr[i];
  j = i - 1;
  while (j >= 0 && arr[j] > key) {
    arr[j + 1] = arr[j--];
  }
  arr[j + 1] = key;
}
console.log(arr);
