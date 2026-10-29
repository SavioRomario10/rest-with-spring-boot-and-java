package io.savioromario10.rest_spring_java.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String password;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_loched")
    private Boolean accountNonLocked;

    @Column(name = "credebtials_non_expired")
    private Boolean credebtialsNonExpired;

    @Column
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permission",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn (name = "id_permission"))
    private List<Permission> permissions;

    public User() {}

    private List<String> getRoles(){

        List<String> roles = new ArrayList<>();
        for(Permission permission: permissions){
            roles.add(permission.getDescription());
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.userName;
    }
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return this.accountNonExpired;
    }
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public Boolean getCredebtialsNonExpired() {
        return credebtialsNonExpired;
    }
    public void setCredebtialsNonExpired(Boolean credebtialsNonExpired) {
        this.credebtialsNonExpired = credebtialsNonExpired;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    public void setUsername(String username) {
        this.userName = username;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getFullName(), user.getFullName()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(isAccountNonExpired(), user.isAccountNonExpired()) &&
                Objects.equals(isAccountNonLocked(), user.isAccountNonLocked()) &&
                Objects.equals(getCredebtialsNonExpired(), user.getCredebtialsNonExpired()) &&
                Objects.equals(isEnabled(), user.isEnabled()) &&
                Objects.equals(getPermissions(), user.getPermissions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getFullName(), getPassword(), isAccountNonExpired(),
                isAccountNonLocked(), getCredebtialsNonExpired(), isEnabled(), getPermissions());
    }
}