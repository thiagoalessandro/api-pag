package br.com.tcb.api.controller.response;

import br.com.tcb.api.controller.enums.ValidateTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateAttributeDTO {
    private String group;
    private String field;
    private ValidateTypeEnum validateType;

    public ValidateAttributeDTO(String group, String field, ValidateTypeEnum validateType) {
        this.group = group;
        this.field = field;
        this.validateType = validateType;
    }
}
