const binarySearch = (array, target) => {
  let left = 0,
    right = array.length - 1,
    pivot;
  while (left <= right) {
    pivot = Math.floor(left + (right - left) / 2);
    if (array[pivot] == target) return pivot;
    if (target < array[pivot]) right = pivot - 1;
    else left = pivot + 1;
  }
  return -1;
};

console.log(binarySearch([-1, 0, 3, 5, 9, 12], 9));
console.log(binarySearch([-1, 0, 3, 5, 9, 12], 12));
console.log(binarySearch([-1, 0, 3, 5, 9, 12], 2));
