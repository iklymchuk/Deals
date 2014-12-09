package ua.ukrdev.deal.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@javax.persistence.Entity
@javax.persistence.Table(name="USERS")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @NotEmpty
    @Size(min = 4, max = 20)
    @Column(name="fname")
    private String fname;

    @NotEmpty
    @Size(min = 4, max = 20)
    @Column(name="lname")
    private String lname;

    @NotEmpty
    @Size(min = 4, max = 20)
    @Column(name="username")
    private String username;


    @NotEmpty
	@Size(min = 4, max = 20)
    @Column(name="password")
	private String password;

    @NotEmpty
    @Size(min = 4, max = 20)
    @Transient
	private String confirmPassword;

	@NotEmpty
	@Email
    @Column(name="email")
	private String email;


    @Column(name="shopname")
    private String shopname;

    @Column(name="balance")
    private Integer balance;

    @NotEmpty
    @Column(name="role")
    private String role;

    @Column(name="photo")
    private String photo;
    
    @NotEmpty
    @Column(name="islock")
    private String islock;
    
    @Size(min = 4, max = 20)
    @Column(name="assign")
    private String assign;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

	public String getIslock() {
		return islock;
	}

	public void setIslock(String islock) {
		this.islock = islock;
	}

	public String getAssign() {
		return assign;
	}

	public void setAssign(String assign) {
		this.assign = assign;
	}
}