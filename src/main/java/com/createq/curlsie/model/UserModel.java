package com.createq.curlsie.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;

@Entity
@Table(name="users")
public class UserModel {

        public enum Role{
                ROLE_USER,
                ROLE_ADMIN
        };

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Size( max=30)
        private String firstName;

        @NotBlank
        @Size( max=30)
        private String lastName;

        @NotBlank
        @Email
        private String email;

        private boolean enabled = true;

        @NotBlank
        @Size( max=30)
        private String username;

        @NotBlank
        private String password;

        @Transient
        private String confirmPassword;

        @Enumerated(EnumType.STRING)
        private Role role;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Role getRole() {
                return role;
        }

        public void setRole(Role role) {
                this.role = role;
        }

        public String getConfirmPassword() {
                return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword) {
                this.confirmPassword = confirmPassword;
        }

        public boolean isEnabled() {
                return enabled;
        }

        public void setEnabled(boolean enabled) {
                this.enabled = enabled;
        }
}


