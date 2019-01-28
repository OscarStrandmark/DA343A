package f03;
import java.io.*;

class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
    private Person partner;

    
    public Person() {
        this("Okänd");
    }

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    public Person getPartner() {
        return partner;
    }

    public void together(Person partner) {
        setPartner(partner);
        partner.setPartner(this);
    }

    public void separate() {
        partner.setPartner(null);
        setPartner(null);
    }

    public String toString() {
        if (partner != null)
            return (name + " är tillsammans med " + partner.getName());
        else
            return (name + " är singel");
    }
}