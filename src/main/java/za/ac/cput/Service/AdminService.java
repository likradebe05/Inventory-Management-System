package za.ac.cput.Service;

import za.ac.cput.Repository.AdminRepository;
import za.ac.cput.Domain.Admin;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import za.ac.cput.Util.Helper;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    private final AdminRepository adminRepo;

    @Autowired
    public AdminService(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public Admin create(Admin obj) {
        // the Helper class to hash the password
        obj.setPassword(Helper.hashPassword(obj.getPassword()));
        return adminRepo.save(obj);
    }

    @Override
    public Admin read(String adminId) {
        return adminRepo.findById(adminId).orElse(null);
    }

    @Override
    public Admin update(Admin obj) {
        return adminRepo.save(obj);
    }

    @Override
    public boolean delete(String adminId) {
        if (adminRepo.existsById(adminId)) {
            adminRepo.deleteById(adminId);
            return true;
        }
        return false;
    }

    @Override
    public List<Admin> getAll() {
        return adminRepo.findAll();
    }

    public Admin findByUserName(String userName) {
        return adminRepo.findByUserName(userName).orElse(null);
    }
}
