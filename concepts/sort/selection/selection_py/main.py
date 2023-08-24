def selection_sort(arr: list):
  for i in range(len(arr)):
    min_index = i

    for j in range(i + 1, len(arr)):
      if (arr[j] < arr[min_index]):
        min_index = j

    arr[i], arr[min_index] = arr[min_index], arr[i]


arr = [60, 20, 54, 23, 12, 43, 23, 1]
selection_sort(arr)
print(arr)
