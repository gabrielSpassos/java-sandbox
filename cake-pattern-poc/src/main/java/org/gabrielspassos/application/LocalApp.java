package org.gabrielspassos.application;

import org.gabrielspassos.repository.LocalAccountRepositoryComponent;
import org.gabrielspassos.repository.LocalUserRepositoryComponent;
import org.gabrielspassos.service.AccountServiceComponent;
import org.gabrielspassos.service.TransactionServiceComponent;
import org.gabrielspassos.service.UserServiceComponent;

public class LocalApp implements
        AccountServiceComponent, LocalAccountRepositoryComponent,
        UserServiceComponent, LocalUserRepositoryComponent,
        TransactionServiceComponent {
}
