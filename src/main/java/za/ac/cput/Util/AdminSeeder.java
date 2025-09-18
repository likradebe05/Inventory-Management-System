package za.ac.cput.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.ac.cput.Domain.Admin;
import za.ac.cput.Domain.Role;
import za.ac.cput.Service.AdminService;

/**
 * Database seeder to create initial admin accounts
 * This will run automatically when the application starts
 */
@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        createDefaultAdminIfNotExists();
    }

    private void createDefaultAdminIfNotExists() {
        // Check if any admin exists
        if (adminService.getAll().isEmpty()) {
            try {
                // Create default admin account with simple credentials
                Admin defaultAdmin = new Admin.Builder()
                        .setUserName("admin@admin.com")
                        .setPassword("admin") // Will be hashed by AdminService.create()
                        .setRole(Role.ADMIN)
                        .setEnabled(true)
                        .setAccountNonExpired(true)
                        .setAccountNonLocked(true)
                        .setCredentialsNonExpired(true)
                        .build();

                adminService.create(defaultAdmin);
                
                System.out.println("✅ Default admin created:");
                System.out.println("   Email: admin@admin.com");
                System.out.println("   Password: admin");
                System.out.println("   🔗 Login at: http://localhost:3000/login");
                
            } catch (Exception e) {
                System.err.println("❌ Failed to create default admin: " + e.getMessage());
            }
        } else {
            System.out.println("ℹ️  Admin accounts already exist. Skipping admin creation.");
            System.out.println("   Available admin credentials:");
            System.out.println("   • admin@admin.com / admin");
            System.out.println("   • admin@test.com / admin123");
        }
    }
}