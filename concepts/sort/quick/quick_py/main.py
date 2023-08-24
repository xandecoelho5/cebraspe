def quick_sort(arr):
  if len(arr) <= 1:
    return arr

  pivot = arr[-1]
  left = []
  right = []

  for element in arr[:-1]:
    if element <= pivot:
      left.append(element)
    else:
      right.append(element)

  return quick_sort(left) + [pivot] + quick_sort(right)


def in_place_quick_sort(arr, low, high):
  if low < high:
    pivot_index = partition(arr, low, high)

    in_place_quick_sort(arr, low, pivot_index - 1)
    in_place_quick_sort(arr, pivot_index + 1, high)


def partition(arr, low, high):
  pivot = arr[high]
  i = low - 1

  for j in range(low, high):
    if arr[j] <= pivot:
      i += 1
      arr[i], arr[j] = arr[j], arr[i]

  arr[i + 1], arr[high] = arr[high], arr[i + 1]
  return i + 1


array = [64, 34, 25, 12, 22, 11, 90]
sorted_array = quick_sort(array)
print(sorted_array)
