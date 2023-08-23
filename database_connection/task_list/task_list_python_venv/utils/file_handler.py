import os
import pickle


class FileHandler:
  _file_name = "resources/task_list"

  def write_to_file(self, obj):
    with open(self._file_name, "wb") as file:
      pickle.dump(obj, file)
    print(f'Object successfully saved to "{self._file_name}"')

  def read_from_file(self):
    try:
      with open(self._file_name, "rb") as file:
        return pickle.load(file)
    except:
      return []
