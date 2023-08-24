def binary_search(arr, target):
  left = 0
  right = len(arr) - 1
  while (left <= right):
    pivot = (left + right) // 2
    if (arr[pivot] == target):
      return pivot
    if (arr[pivot] < target):
      left = pivot + 1
    else:
      right = pivot - 1
  return -1


print(binary_search([-1, 0, 3, 5, 9, 12], 9))
print(binary_search([-1, 0, 3, 5, 9, 12], 12))
print(binary_search([-1, 0, 3, 5, 9, 12], 2))
