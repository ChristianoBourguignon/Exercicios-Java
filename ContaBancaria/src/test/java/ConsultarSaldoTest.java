import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Models.ContaBancaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsultarSaldoTest {
    ContaControllers cc;

    @BeforeEach
    public void config() {
        ContaBancaria con = new ContaBancaria("NUBANK",new BigDecimal("200"),new BigDecimal("200"));
        cc = new ContaControllers(con);
    }

    @Test
    public void ConsultarSaldoTotal() {
        //Actions
        BigDecimal saldo = this.cc.getSaldo();

        //Asserts
        assertEquals(0,saldo.compareTo(new BigDecimal("200.00")));
    }

    @Test
    public void ConsultarChequeEspecial() {
        //Preparation
        BigDecimal saldo = new BigDecimal("400.00");

        //Actions
        cc.RetirarSaldo(saldo);

        //Asserts
        assertTrue(cc.getUsandoChequeEspecial());
    }
}
