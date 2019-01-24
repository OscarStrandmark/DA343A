package l02;

public class Person implements Comparable<Person> {
    private String id;
    private String firstName;
    private String lastName;
    
    public Person( String id, String firstName, String lastName ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Person && !(obj == null)) {
    		if(id == ((Person) obj).getId()) {
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
    
    public String toString() {
        return id + " " + firstName + " " + lastName;
    }

	@Override
	public int compareTo(Person p) {
		return p.getId().compareTo(this.getId());
	} 
}
