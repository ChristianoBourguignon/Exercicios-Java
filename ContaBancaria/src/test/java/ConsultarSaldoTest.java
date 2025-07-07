import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Models.ContaBancaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ConsultarSaldoTest {
    ContaControllers cc;

    @BeforeEach
    public void config() {
        ContaBancaria con = new ContaBancaria("NUBANK",new BigDecimal("200"));
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
        BigDecimal saldo = new BigDecimal("200.00");
        BigDecimal chequeEspecial = new BigDecimal("50.00");

        //Actions
        cc.retirarSaldo(saldo);

        //Asserts
        assertEquals(chequeEspecial,cc.getChequeEspecial());
    }

    @Test
    public void getSaldoTotalAposRetiradaParcialDoChequeEspecial() {
        //Preparation
        BigDecimal saldoTotalEsperado = new BigDecimal("30.00");


        //Actions
        cc.retirarSaldo(new BigDecimal("220"));

        //Asserts
        assertEquals(saldoTotalEsperado, cc.getSaldoTotal());
    }

    @Test
    public void temSaldoPositivoComSaldoZerado() {
        //Preparation
        BigDecimal saldoZerado = new BigDecimal("100.00");

        //Actions
        cc.retirarSaldo(saldoZerado);

        //Asserts
        assertTrue(cc.temSaldoPositivo());
    }

}
