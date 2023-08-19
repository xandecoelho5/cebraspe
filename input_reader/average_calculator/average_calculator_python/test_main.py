import main as m


def test_should_calculate_average_of_numbers():
  assert m.calculate_average_from_list([10, 15, 20, 25]) == 17.50
