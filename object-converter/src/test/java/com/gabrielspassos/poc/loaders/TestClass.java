package com.gabrielspassos.poc.loaders;

import com.gabrielspassos.poc.dto.AccountDTO;
import com.gabrielspassos.poc.dto.SavingAccountDTO;
import com.gabrielspassos.poc.memory.loaders.InMemoryConverterClass;

public class TestClass implements InMemoryConverterClass<AccountDTO, SavingAccountDTO> {

    public SavingAccountDTO convert(AccountDTO input) {
        SavingAccountDTO output = new SavingAccountDTO();
        output.setAgency(input.getAgency());
        output.setNumber(input.getNumber());
        output.setDigit(input.getDigit());
        return output;
    }

}
