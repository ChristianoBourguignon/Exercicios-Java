import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Models.ContaBancaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdicionarSaldoTest {
    ContaControllers cc;

    @BeforeEach
    public void config() {
        ContaBancaria con = new ContaBancaria("NUBANK",new BigDecimal("200"),new BigDecimal("200"));
        cc = new ContaControllers(con);
    }
    @Test
    public void adicionarSaldo() {
        //Actions
        cc.AdicionarSaldo(new BigDecimal("200"));

        //Asserts
        assertEquals(0, cc.getSaldoTotal().compareTo(new BigDecimal("600.00")));
    }
}
