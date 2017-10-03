package nightroomcreation.id.realmdemo2.database.table;

/**
 * Created by iand on 29/09/17.
 */

public interface RepositoryTable {

    String ID = "id";

    interface Unversity {
        String STUDENT = "student";
        String NAME = "name";
    }

    interface Student {
        String NAME = "name";
        String AGE = "age";
        String EMAIL = "email";
        String BOOKS = "books";
        String LESSONS = "lessons";
    }
}
