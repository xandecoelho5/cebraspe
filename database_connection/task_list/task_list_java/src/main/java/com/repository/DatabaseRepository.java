package com.repository;

import static com.utils.ThrowingConsumer.consumerWrapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.database.DbConnection;
import com.model.Task;

public class DatabaseRepository implements TaskRepository {

  private final DbConnection dbConnection;

  private final String insertQuery = "INSERT INTO task(title, description, created_at) VALUES(?, ?, ?)";
  private final String updateQuery = "UPDATE task SET title = ?, description = ? WHERE id = ?";
  private final String deleteQuery = "DELETE FROM task WHERE id = ?";
  private final String findAllQuery = "SELECT * FROM task";
  private final String findByIdQuery = "SELECT * FROM task WHERE id = ?";

  public DatabaseRepository(DbConnection dbConnection) {
    this.dbConnection = dbConnection;
  }

  @Override
  public void insert(Task task) {
    executeUpdateStatement(insertQuery, consumerWrapper(ps -> {
      ps.setString(1, task.getTitle());
      ps.setString(2, task.getDescription());
      if (dbConnection.hasDateTimeType()) {
        ps.setTimestamp(3, Timestamp.valueOf(task.getCreatedAt()));
      } else {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ps.setString(3, sdf.format(Timestamp.valueOf(task.getCreatedAt())));
      }
    }, SQLException.class));
  }

  @Override
  public void update(int id, Task task) {
    executeUpdateStatement(updateQuery, consumerWrapper(ps -> {
      ps.setString(1, task.getTitle());
      ps.setString(2, task.getDescription());
      ps.setInt(3, id);
    }, SQLException.class));
  }

  @Override
  public void deleteById(int id) {
    executeUpdateStatement(deleteQuery, consumerWrapper(ps -> {
      ps.setInt(1, id);
    }, SQLException.class));
  }

  @Override
  public Optional<Task> findById(int id) {
    var tasks = executeQueryStatement(findByIdQuery, consumerWrapper(ps -> {
      ps.setInt(1, id);
    }, SQLException.class));

    return Optional.ofNullable(tasks.get(0));
  }

  @Override
  public List<Task> findAll() {
    return executeQueryStatement(findAllQuery, ps -> {
    });
  }

  public void executeUpdateStatement(String query, Consumer<PreparedStatement> setParameters) {
    try (PreparedStatement ps = dbConnection.getCon().prepareStatement(query)) {
      setParameters.accept(ps);
      ps.executeUpdate();
    } catch (SQLException ex) {
      System.err.format("Erro no banco de dados: %s\n%s", ex.getSQLState(), ex.getMessage());
    }
  }

  public List<Task> executeQueryStatement(String query, Consumer<PreparedStatement> setParameters) {
    List<Task> result = new ArrayList<>();

    try (PreparedStatement ps = dbConnection.getCon().prepareStatement(query)) {
      setParameters.accept(ps);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        var createdAt = dbConnection.hasDateTimeType() ? rs.getTimestamp(4) : Timestamp.valueOf(rs.getString(4));

        Task task = new Task(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            createdAt.toLocalDateTime());
        result.add(task);
      }
    } catch (SQLException ex) {
      System.err.format("Erro ao buscar no banco de dados: %s\n%s", ex.getSQLState(), ex.getMessage());
    }
    return result;
  }
}
