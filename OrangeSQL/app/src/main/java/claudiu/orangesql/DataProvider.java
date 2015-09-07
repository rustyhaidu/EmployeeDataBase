package claudiu.orangesql;

/**
 * Created by claudiu.haidu on 7/24/2015.
 */
// TODO - CODE REVIEW - andrei | 9/7/15 - The name of the class does not resemble its contents. This is you User class!!
public class DataProvider {

    private String name;
    private String surname;
    private String gender;
    private String birthdate;

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }


    // TODO - CODE REVIEW - andrei | 9/7/15 - Try to have a consistent order of members and methods in your classes. The constructor should be before the rest of the methods for readability's sake.

    public DataProvider(String name, String surname, String gender,String birthdate){
        this.name = name;
        this.surname=surname;
        this.gender=gender;
        this.birthdate=birthdate;
    }

}
