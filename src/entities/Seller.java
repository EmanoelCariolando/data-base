package entities;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {
    Integer id;
    String name;
    String email;
    Date birthDate;
    Double baseSalary;

    private Department department;

    public Seller(){
    }
    public Seller(Double baseSalary, Date birthDate, String email, Integer id, String name) {
        this.baseSalary = baseSalary;
        this.birthDate = birthDate;
        this.email = email;
        this.id = id;
        this.name = name;
    }


    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Seller" + ": " +
                "name = " + name +
                ", id = " + id +
                ", baseSalary = " + baseSalary +
                ", email = " + email +
                ", birthDate = " + birthDate +
                ", department = " + department;
    }
}
