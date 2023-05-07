package com.gabrielspassos.poc;

import com.gabrielspassos.poc.dto.AccountDTO;
import com.gabrielspassos.poc.dto.SavingAccountDTO;

public class ConverterFromAccountDTOToSavingAccountDTO {
    public SavingAccountDTO convert(AccountDTO input) {
        SavingAccountDTO output = new SavingAccountDTO();
        output.setAgency(input.getAgency());
        output.setNumber(input.getNumber());
        output.setDigit(input.getDigit());
        return output;
    }
}

