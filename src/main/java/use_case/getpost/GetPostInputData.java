package use_case.getpost;

/**
 * The Input Data for the Get Post Use Case.
 */
public class GetPostInputData {

    private final String entryID;

    public GetPostInputData(String entryID) {
        this.entryID = entryID;
    }

    public String getEntryID() {
        return entryID;
    }
}
