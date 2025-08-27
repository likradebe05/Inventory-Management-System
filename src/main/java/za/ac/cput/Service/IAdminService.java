package za.ac.cput.Service;
import java.util.List;
import za.ac.cput.Domain.Admin;


public interface IAdminService extends IService <Admin, String>{
    List<Admin> getAll();
}
