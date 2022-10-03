package zoo.manager.exceptions.models;

public class RecordDuplicateException extends RuntimeException  {

    public RecordDuplicateException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Record with this name already exist in an entity";
    }
}
