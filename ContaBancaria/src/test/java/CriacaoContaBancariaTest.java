import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Exception.ExceptionCustom;
import br.com.contaBancaria.Exception.NoExistsAccountException;
import br.com.contaBancaria.Models.ContaBancaria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CriacaoContaBancariaTest {
    ContaControllers cc = null;
    ContaBancaria con = null;

    @Test
    public void ContaNaoExiste(){
        //Actions
        NoExistsAccountException ex = assertThrows(NoExistsAccountException.class, () -> {
            new ContaControllers(null);
        });

        //Asserts
        assertInstanceOf(NoExistsAccountException.class, ex);
    }
}
