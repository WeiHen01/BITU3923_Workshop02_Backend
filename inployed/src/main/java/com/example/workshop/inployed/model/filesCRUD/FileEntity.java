package com.example.workshop.inployed.model.filesCRUD;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="user_resume")
public class FileEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name = "fileNo")
	private int Number;
	
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column (name = "id")
    private String id;
	
	@Column (name = "name")
    private String name;

	@Column (name = "type")
    private String contentType;

	@Column (name = "size")
    private Long size;

    @Lob
    @Column (name = "data")
    private byte[] data;

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
}
