def calculate_average_from_list(numbers) -> float:
  if (not numbers):
    return 0.0
  return sum(numbers) / len(numbers)


def main():
  quantity_of_numbers = int(input("Quantos números deseja inserir? "))
  numbers_for_calculate = [float(input(str.format("Insira o número {}: ", i + 1)))
                           for i in range(quantity_of_numbers)]
  average = calculate_average_from_list(numbers_for_calculate)
  print(f"A média dos números inseridos é: {average:.2f}", )


if __name__ == '__main__':
  main()
