package employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

    //@NotBlank(message = "must not empty")
    @Name(message = "bad name")
    private String name;
}
