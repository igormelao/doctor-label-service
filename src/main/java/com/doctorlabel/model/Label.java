package com.doctorlabel.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
* 
* This class is a Entity Model that represents an object of type Label
* 
* The Label is a conditions that determine some conditions of one or more desires.
* 
* The structurer of one Label it's described below:
* 
*  <ol> 
*  	<li>String id; (ID)</li>
*  	<li>String description; (nullable = false)</li>
*  	<li>LocalDateTime dateCreate</li>
*  </ol>
* 
*  The methods that Label contains are:
*  
*  <ol>
*  	
*  	<li> Getters and Setter for all attributes</li>
*   <li> hashCode() </li>
*   <li> Equals()</li>
*   <li> toString()</li>
*  </ol>
*  
*  Constructors:
*  
*  <ol>
*  	<li>Default empty constructor Label(){}</li>
*  	<li>Override constructor Label(String id, String description, LocalDateTime dateCreate)</li>
*  </ol>
*  
* 
* @author Igor Melão (igormelao@gmail.com)
* @Date:  14-03-2021
* @since  0.0.1-SNAPSHOT
* 
* 
*/

@Entity
public class Label {

	/**
	 * <p>The private unique identification Id of  Label.<p>
	 * <p>This is a String because this values for this field gonna be  
	 * the abreviation of the labe</p>
	 * 
	 * <p>Example:</p>
	 * <ol>
	 * 	<li>A09</li>
	 * </ol>
	 *  
	 */
	@Id
	private String id;
	
	/**
	 * <p>The private description of a Label.<p>
	 * <p>This descriptions contains more details of the {@link com.doctorlabel.model.Label.id} means</p>
	 * <p> This is mandatory field</p>
	 * 
	 * <p>Example:</p>
	 * <ol>
	 * 	<li>Infectious gastroenteritis and colitis, unspecified</li>
	 * </ol>
	 *  
	 */
	@Column(nullable = false)
	private String description;
	
	/**
	 * <p>The private date of creation of a Label.<p>
	 * <p> This is not mandatory field</p>
	 * 
	 * <p>Example:</p>
	 * <ol>
	 * 	<li>14-03-201 14:30:33</li>
	 * </ol>
	 *  
	 */
	private LocalDateTime dateCreate = LocalDateTime.now();

	public Label() {
	};

	public Label(String id, String description, LocalDateTime dateCreate) {

		this.id = id;
		this.description = description;
		this.dateCreate = dateCreate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDateTime dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreate == null) ? 0 : dateCreate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Label other = (Label) obj;
		if (dateCreate == null) {
			if (other.dateCreate != null)
				return false;
		} else if (!dateCreate.equals(other.dateCreate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Label [id=" + id + ", description=" + description + ", dateCreate=" + dateCreate + "]";
	}

}
