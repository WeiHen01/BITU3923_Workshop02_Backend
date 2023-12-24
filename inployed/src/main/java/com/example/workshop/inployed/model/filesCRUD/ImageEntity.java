package com.example.workshop.inployed.model.filesCRUD;

import org.hibernate.annotations.GenericGenerator;

import com.example.workshop.inployed.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_image")
public class ImageEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "img_no")
	private int Number;
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column (name = "img_id")
    private String id;
	
	@Column (name = "img_name")
    private String name;

	@Column (name = "img_type")
    private String contentType;

	@Column (name = "img_size")
    private Long size;

    @Lob
    @Column (name = "img_data")
    private byte[] data;
    
    @ManyToOne
	@JoinColumn(name = "UserID")
	private User userID;

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public User getUserID() {
		return userID;
	}

	public void setUserID(User userID) {
		this.userID = userID;
	}
}
