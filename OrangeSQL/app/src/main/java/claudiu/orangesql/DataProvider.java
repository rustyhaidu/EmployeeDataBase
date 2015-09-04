package claudiu.orangesql;

/**
 * Created by claudiu.haidu on 7/24/2015.
 */
public class DataProvider {

    private String name;
    private String mob;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }



    public DataProvider(String name, String mob, String email){
        this.name = name;
        this.mob=mob;
        this.email=email;
    }

}
