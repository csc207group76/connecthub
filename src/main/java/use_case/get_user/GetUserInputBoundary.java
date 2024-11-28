package use_case.get_user;

import entity.User;

public interface GetUserInputBoundary {
    User getUserByID(GetUserInputData getUserInputData);

    User getCurrentUser();
}
