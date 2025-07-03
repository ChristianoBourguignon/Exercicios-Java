import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Models.ContaBancaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RetirarSaldoTest {
    ContaControllers cc;

    @BeforeEach
    public void config() {
        ContaBancaria con = new ContaBancaria("NUBANK",new BigDecimal("200"),new BigDecimal("200"));
        cc = new ContaControllers(con);
    }

    @Test
    public void retirarSaldo() {
        //Preparation
        BigDecimal saldo = new BigDecimal("0");

        //Actions
        cc.RetirarSaldo(new BigDecimal("200.00"));

        //Asserts
        assertEquals(0,this.cc.getSaldo().compareTo(saldo));
    }

    @Test
    public void retirarSaldoTotal() {
        //Preparation
        BigDecimal saldo = new BigDecimal("0.00");

        //Actions
        cc.RetirarSaldo(new BigDecimal("300.00"));

        //Asserts
        assertEquals(0,this.cc.getSaldo().compareTo(saldo));
    }

    @Test
    public void consultarChequeEspecialAposRetirada() {
        //Preparation
        BigDecimal saldo = new BigDecimal("0.00");

        //Actions
        cc.RetirarSaldo(new BigDecimal("400.00"));

        //Asserts
        assertEquals(0,this.cc.getChequeEspecial().compareTo(saldo));
    }

    @Test
    public void consultarUsandoChequeEspecial() {
        //Preparation
        BigDecimal saldo = new BigDecimal("400.00");

        //Actions
        cc.RetirarSaldo(saldo);

        //Asserts
        assertTrue(cc.getUsandoChequeEspecial());
    }
}
