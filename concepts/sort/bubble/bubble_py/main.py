def sort_arr(arr):
  interactions = 0
  for i in range(len(arr) - 1, -1, -1):
    interactions += 1
    for j in range(i - 1, -1, -1):
      interactions += 1
      if (arr[i] < arr[j]):
        arr[i], arr[j] = arr[j], arr[i]
  print(interactions)


def bubble_sort_reverse(arr):
  interactions = 0
  n = len(arr)
  for i in range(n - 1, 0, -1):
    interactions += 1
    for j in range(n - 1, n - i - 1, -1):
      interactions += 1
      if arr[j] < arr[j - 1]:
        arr[j], arr[j - 1] = arr[j - 1], arr[j]
  print(interactions)


def bubble_sort(arr):
  interactions = 0
  n = len(arr)
  for i in range(n):
    interactions += 1
    for j in range(0, n - i - 1):
      interactions += 1
      if arr[j] > arr[j + 1]:
        arr[j], arr[j + 1] = arr[j + 1], arr[j]
  print(interactions)


array = [60, 20, 54, 23, 12, 43, 23, 1]
# sort_arr(array)
# print(array)

bubble_sort(array)
print(array)
