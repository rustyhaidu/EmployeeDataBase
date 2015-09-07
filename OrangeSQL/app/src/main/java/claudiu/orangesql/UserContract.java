package claudiu.orangesql;

/**
 * Created by claudiu.haidu on 7/16/2015.
 */
// TODO - CODE REVIEW - andrei | 9/7/15 - What is the point of this structure? Why do we have a UserContract class? What is the purpose of the NewUserInfo class? Why does it have to be part of UserContract?
public class UserContract {

    public static abstract class NewUserInfo{

        public static final String EMPLOYEE_NAME = "employee_name";
        public static final String EMPLOYEE_SURNAME = "employee_surname";
        public static final String EMPLOYEE_GENDER = "employee_gender";
        public static final String EMPLOYEE_BIRTHDATE = "employee_birthdate";
        public static final String TABLE_NAME = "user_info";

    }
}
