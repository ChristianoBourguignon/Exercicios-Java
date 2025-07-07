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
        ContaBancaria con = new ContaBancaria("NUBANK",new BigDecimal("200"));
        cc = new ContaControllers(con);
    }

    @Test
    public void adicionarSaldo() {
        //Preparation
        BigDecimal valorEsperado = new BigDecimal("450.00");

        //Actions
        cc.adicionarSaldo(new BigDecimal("200"));

        //Asserts
        assertEquals(valorEsperado, cc.getSaldoTotal());
    }

    @Test
    public void adicionarSaldoComChequeEspecial() {
        //Preparation
        ContaBancaria conta = new ContaBancaria("NUBANK",new BigDecimal("1.00"));
        cc = new ContaControllers(conta);
        cc.retirarSaldo(new BigDecimal("2.00"));

        BigDecimal saldoChequeVerificador = new BigDecimal("50.00");
        BigDecimal saldoVerificador = new BigDecimal("79.00");
        BigDecimal saldoTotalVerificador = new BigDecimal("129.00");


        //Actions
        cc.adicionarSaldo(new BigDecimal("100.00"));

        //Asserts
        assertEquals(1, cc.getSaldo().compareTo(BigDecimal.ZERO));
        assertEquals(saldoTotalVerificador, cc.getSaldoTotal().setScale(2));
        assertFalse(cc.getUsandoChequeEspecial());
        assertEquals(saldoVerificador, cc.getSaldo().setScale(2));
        assertEquals(saldoChequeVerificador, cc.getChequeEspecial());

    }

    @Test
    public void retirarSaldoEAdicionarSaldo() {
        //Preparation
        BigDecimal saldoAdicionado = new BigDecimal("20.00");
        BigDecimal saldoRetirado = new BigDecimal("240.00");
        BigDecimal saldoVerificador = new BigDecimal("0.00");
        BigDecimal chequeVerificador = new BigDecimal("30.00");

        //Actions
        cc.adicionarSaldo(saldoAdicionado);
        cc.retirarSaldo(saldoRetirado);

        //Asserts
        assertEquals(saldoVerificador, cc.getSaldo());
        assertEquals(chequeVerificador, cc.getChequeEspecial());
    }
}
