const array = [60, 20, 54, 23, 12, 43, 23, 1];

const quickSort = (arr) => {
  if (arr.length <= 1) {
    return arr;
  }

  const pivot = arr[arr.length - 1];
  const left = [];
  const right = [];

  for (let i = 0; i < arr.length - 1; i++) {
    if (arr[i] < pivot) {
      left.push(arr[i]);
    } else {
      right.push(arr[i]);
    }
  }

  return [...quickSort(left), pivot, ...quickSort(right)];
};

function inPlaceQuickSort(arr, left = 0, right = arr.length - 1) {
  if (left < right) {
    const pivotIndex = partition(arr, left, right);

    // Recursively sort the partitions
    inPlaceQuickSort(arr, left, pivotIndex - 1);
    inPlaceQuickSort(arr, pivotIndex + 1, right);
  }
}

function partition(arr, left, right) {
  const pivotValue = arr[right];
  let pivotIndex = left;

  for (let i = left; i < right; i++) {
    if (arr[i] < pivotValue) {
      // Swap elements
      [arr[i], arr[pivotIndex]] = [arr[pivotIndex], arr[i]];
      pivotIndex++;
    }
  }

  // Swap pivot with element at pivotIndex
  [arr[pivotIndex], arr[right]] = [arr[right], arr[pivotIndex]];
  return pivotIndex;
}

console.log(quickSort(array));
