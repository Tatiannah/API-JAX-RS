package example.crud.dao;



import example.crud.model.Employe;
import example.crud.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAO {
    private final Connection connection;

    public EmployeDAO() throws SQLException {
        connection = DBUtil.getConnection();
    }

    // CREATE
    public void addEmploye(Employe employe) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person(name, salaire,observation) VALUES (?, ?,"
                    + "CASE \n" +
                "WHEN  ? < 1000 THEN 'Mediocre'\n" +
                "WHEN ? < 5000 AND salaire >= 1000 THEN 'moyen'\n" +
                "ELSE 'grand'\n" +
                "    END)");
            preparedStatement.setString(1, employe.getName());
            preparedStatement.setInt(2, employe.getSalaire());
            preparedStatement.setInt(3, employe.getSalaire());
            preparedStatement.setInt(4, employe.getSalaire());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Employe> getAllEmployes() {
        List<Employe> employes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                Employe employe = new Employe();
                employe.setId(resultSet.getInt("id"));
                employe.setName(resultSet.getString("name"));
                employe.setSalaire(resultSet.getInt("salaire"));
                employe.setObservation(resultSet.getString("observation"));
                employes.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employes;
    }
    
     public String getMaxMinSalary() {
         String maxS = null,minS = null;String SomValSal = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MIN(salaire) AS salaire_minimum, MAX(salaire) AS salaire_maximum , SUM(salaire) as Somme_Sal FROM person;");
            while (resultSet.next()) {
                
                int salMin = resultSet.getInt("salaire_minimum");
                int salMax = resultSet.getInt("salaire_maximum");
                int SomSal = resultSet.getInt("Somme_Sal");
                 maxS= Integer.toString(salMax);
                 minS= Integer.toString(salMin);
                 SomValSal = Integer.toString(SomSal);
             
            }
        } catch (SQLException e) {
            return null;
        }
          return "Salaire le plus important :"+maxS+ " Ar -----" +"------" + "Salaire le plus faible :"+minS+ "Ar -----" +"------" + "Salaire Total :"+SomValSal+"Ar";    
    }

    public Employe getEmployeById(int id) {
        Employe user = new Employe();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSalaire(resultSet.getInt("salaire"));
                 user.setObservation(resultSet.getString("observation"));
            }
        } catch (SQLException e) {
            
        }
        return user;
    }

    // UPDATE
    public void updateEmploye(Employe user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET name=?, salaire=? ,observation = \n" +
"    CASE \n" +
"        WHEN ? < 1000 THEN 'mediocre'\n" +
"        WHEN ? >= 2000 AND salaire < 4000 THEN 'moyen'\n" +
"        ELSE 'grand'\n" +
"    END WHERE id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getSalaire());
            preparedStatement.setInt(3, user.getSalaire());
            preparedStatement.setInt(4, user.getSalaire());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteEmploye(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
