package example.crud.service;

import example.crud.model.Employe;
import example.crud.dao.EmployeDAO;
import java.sql.SQLException;
import java.util.List;

public class EmployeService {
    private final EmployeDAO EmpDao;

    public EmployeService() throws SQLException {
        this.EmpDao = new EmployeDAO();
    }

    public List<Employe> getAllEmp() {
        return EmpDao.getAllEmployes();
    }
    
    public String getMaxMinSalary() {
        return EmpDao.getMaxMinSalary();
    }
    
    

    public Employe getEmpById(int id) {
        return EmpDao.getEmployeById(id);
    }

    public void addEmp(Employe user) {
        EmpDao.addEmploye(user);
    }

    public void updateEmp(Employe user) {
        EmpDao.updateEmploye(user);
    }

    public void deleteEmp(int id) {
        EmpDao.deleteEmploye(id);
    }
}
