package za.ac.cput.Repository;

import za.ac.cput.Domain.Admin;
import java.util.List;


public interface IAdminRepository {
    Admin create(Admin admin);
    Admin read(String adminId);
    Admin update(Admin admin);
    boolean delete(String adminId);
    List<Admin> getAll();


}
