package br.com.insight.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "activity")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Activity {
	
	public enum TypeEnum {
		CURSO, APRESENTAÇÃO, PALESTRA
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;	

	@Column(nullable = false)
    private String type;
	
	@Column(nullable = false)
    private String description;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	 @JsonBackReference
    private User user;
	
	
	
	public Activity() {
		super();
	}
	
	

	public Activity(long id, String type, String description, User user) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.user = user;
	}



	public Activity(String type, String description, User user) {
		// TODO Auto-generated constructor stub
		super();
		this.type = TypeEnum.valueOf(type).name();
		this.description = description;
		this.user = user;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", type=" + type + ", description=" + description + ", user=" + user + "]";
	}

	
	
	
	
	
}
