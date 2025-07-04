import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Models.ContaBancaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        cc.adicionarSaldo(new BigDecimal("200"));

        //Asserts
        assertEquals(0, cc.getSaldoTotal().compareTo(new BigDecimal("600.00")));
    }

    @Test
    public void adicionarSaldoComChequeEspecial() {
        //Preparation
        ContaBancaria conta = new ContaBancaria("NUBANK",new BigDecimal("0"),new BigDecimal("200"));
        conta.setUsandoChequeEspecial(true);
        cc = new ContaControllers(conta);

        //Actions
        cc.adicionarSaldo(new BigDecimal("200"));

        //Asserts
        assertEquals(0, cc.getSaldoTotal().compareTo(BigDecimal.ZERO));
        assertEquals(-1, cc.getSaldoTotal().compareTo(new BigDecimal("200.00")));
        assertFalse(cc.getUsandoChequeEspecial());
        assertEquals(new BigDecimal(0), cc.getSaldo());
        assertEquals(new BigDecimal(0), cc.getChequeEspecial());

    }

    @Test
    public void retirarSaldoEAdicionarSaldo() {
        //Preparation
        BigDecimal saldoAdicionado = new BigDecimal("20.00");
        BigDecimal saldoRetirado = new BigDecimal("230.00");
        BigDecimal saldoVerificador = new BigDecimal("0.00");
        BigDecimal chequeVerificador = new BigDecimal("190.00");

        //Actions
        cc.adicionarSaldo(saldoAdicionado);
        cc.retirarSaldo(saldoRetirado);

        //Asserts
        assertEquals(saldoVerificador, cc.getSaldo());
        assertEquals(chequeVerificador, cc.getChequeEspecial());
    }
}
