import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Models.ContaBancaria;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdicionarSaldoTest {
    ContaBancaria con = new ContaBancaria("NUBANK",new BigDecimal("200"),new BigDecimal("200"));
    ContaControllers cc = new ContaControllers(con);

    @Test
    public void adicionarSaldo() {
        //Actions
        cc.AdicionarSaldo(new BigDecimal("200"));

        //Asserts
        assertEquals(0, cc.getSaldoTotal().compareTo(new BigDecimal("600.00")));
    }
}
