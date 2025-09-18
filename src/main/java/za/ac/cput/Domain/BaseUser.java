package za.ac.cput.Domain;

import jakarta.persistence.*;

/**
 * Base class for all user types in the system.
 * This will help with Spring Security UserDetails implementation later.
 */
@MappedSuperclass
public abstract class BaseUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @Column(unique = true, nullable = false)
    protected String email;
    
    @Column(nullable = false)
    protected String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;
    
    @Column(nullable = false)
    protected boolean enabled = true;
    
    @Column(nullable = false)
    protected boolean accountNonExpired = true;
    
    @Column(nullable = false)
    protected boolean accountNonLocked = true;
    
    @Column(nullable = false)
    protected boolean credentialsNonExpired = true;

    // Protected constructor for JPA
    protected BaseUser() {}

    // Constructor
    public BaseUser(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public boolean isAccountNonExpired() { return accountNonExpired; }
    public void setAccountNonExpired(boolean accountNonExpired) { this.accountNonExpired = accountNonExpired; }

    public boolean isAccountNonLocked() { return accountNonLocked; }
    public void setAccountNonLocked(boolean accountNonLocked) { this.accountNonLocked = accountNonLocked; }

    public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) { this.credentialsNonExpired = credentialsNonExpired; }

    /**
     * Get the Spring Security authority
     */
    public String getAuthority() {
        return role.getAuthority();
    }

    /**
     * Check if user has admin privileges
     */
    public boolean isAdmin() {
        return role.isAdmin();
    }

    /**
     * Check if user is a customer
     */
    public boolean isCustomer() {
        return role.isCustomer();
    }
}