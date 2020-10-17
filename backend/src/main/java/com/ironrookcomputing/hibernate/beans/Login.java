package com.ironrookcomputing.hibernate.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ironrookcomputing.beans.LoginData;

@Entity
@Table(name="LOGIN")
public class Login {
	public Login() {
		super();
	}
	
	public Login(LoginData loginData) {
		super();
		
		setUsername(loginData.username);
		setPassword(loginData.password);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public String getTokenRefresh() {
		return tokenRefresh;
	}

	public void setTokenRefresh(String tokenRefresh) {
		this.tokenRefresh = tokenRefresh;
	}

	public Date getTokenDateTime() {
		return tokenDateTime;
	}

	public void setTokenDateTime(Date tokenDateTime) {
		this.tokenDateTime = tokenDateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", username=" + username + ", password=" + password + ", registrationDate="
				+ registrationDate + ", enabled=" + enabled + ", tokenValue=" + tokenValue + ", tokenRefresh="
				+ tokenRefresh + ", tokenDateTime=" + tokenDateTime + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
		result = prime * result + ((tokenDateTime == null) ? 0 : tokenDateTime.hashCode());
		result = prime * result + ((tokenRefresh == null) ? 0 : tokenRefresh.hashCode());
		result = prime * result + ((tokenValue == null) ? 0 : tokenValue.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registrationDate == null) {
			if (other.registrationDate != null)
				return false;
		} else if (!registrationDate.equals(other.registrationDate))
			return false;
		if (tokenDateTime == null) {
			if (other.tokenDateTime != null)
				return false;
		} else if (!tokenDateTime.equals(other.tokenDateTime))
			return false;
		if (tokenRefresh == null) {
			if (other.tokenRefresh != null)
				return false;
		} else if (!tokenRefresh.equals(other.tokenRefresh))
			return false;
		if (tokenValue == null) {
			if (other.tokenValue != null)
				return false;
		} else if (!tokenValue.equals(other.tokenValue))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Id
	@Column(name="id", unique=true)
	private int id = 0;
	
	@Column(name="username", unique=true, nullable=false)
	private String username = "";
	
	@Column(name="password", nullable=false)
	private String password = "";
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="registrationdate", nullable=false)
	private Date registrationDate = new Date();
	
	@Column(name="enabled", nullable=false)
	private Boolean enabled = false;
	
	@Column(name="tokenvalue", unique=true, nullable=false)
	private String tokenValue = null;
	
	@Column(name="tokenrefresh", unique=true, nullable=false)
	private String tokenRefresh = null;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="tokendatetime", nullable=false)
	private Date tokenDateTime = null;
	
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name="USER_id", referencedColumnName="id")
	private User user = null;
}
