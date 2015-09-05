package claudiu.orangesql;

/**
 * Created by claudiu.haidu on 7/24/2015.
 */
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




    public DataProvider(String name, String surname, String gender,String birthdate){
        this.name = name;
        this.surname=surname;
        this.gender=gender;
        this.birthdate=birthdate;
    }

}
