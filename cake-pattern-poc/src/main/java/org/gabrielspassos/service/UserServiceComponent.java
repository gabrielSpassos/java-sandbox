package org.gabrielspassos.service;

import org.gabrielspassos.repository.UserRepositoryComponent;

public interface UserServiceComponent extends UserRepositoryComponent {

    default UserService getUserService() {
        return new UserService(getUserRepository());
    }

}
