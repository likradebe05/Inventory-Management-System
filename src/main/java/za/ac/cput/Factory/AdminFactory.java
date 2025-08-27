package za.ac.cput.Factory;

import za.ac.cput.Domain.Admin;


public class AdminFactory {
    public static Admin createAdmin(String UserName, String password,
                                    String role) {
        if (UserName == null || UserName.isEmpty() || password == null || password.isEmpty() || role == null
                || role.isEmpty()) {

            return null; }

        return new Admin.Builder()
                .setUserName(UserName)
                .setPassword(password)
                .setRole(role)
                .build(); }

}
