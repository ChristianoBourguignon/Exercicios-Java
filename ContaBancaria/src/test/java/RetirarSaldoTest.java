import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Exception.DadosInvalidosException;
import br.com.contaBancaria.Exception.ExceptionCustom;
import br.com.contaBancaria.Models.ContaBancaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

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
        cc.retirarSaldo(new BigDecimal("200.00"));

        //Asserts
        assertEquals(0,this.cc.getSaldo().compareTo(saldo));
    }

    @Test
    public void retirarSaldoTotal() {
        //Preparation
        BigDecimal saldo = new BigDecimal("0.00");

        //Actions
        cc.retirarSaldo(new BigDecimal("300.00"));

        //Asserts
        assertEquals(0,this.cc.getSaldo().compareTo(saldo));
    }

    @Test
    public void consultarChequeEspecialAposRetirada() {
        //Preparation
        BigDecimal saldo = new BigDecimal("0.00");

        //Actions
        cc.retirarSaldo(new BigDecimal("400.00"));

        //Asserts
        assertEquals(0,this.cc.getChequeEspecial().compareTo(saldo));
    }

    @Test
    public void consultarUsandoChequeEspecial() {
        //Preparation
        BigDecimal saldo = new BigDecimal("400.00");

        //Actions
        cc.retirarSaldo(saldo);

        //Asserts
        assertFalse(cc.getUsandoChequeEspecial());
    }

    @Test
    public void valorDoChequeAposRetirada() {
        //Preparation
        BigDecimal saldoCheque = new BigDecimal("150.00");

        //Actions
        cc.retirarSaldo(new BigDecimal("250.00"));

        //Asserts
        assertEquals(0,this.cc.getChequeEspecial().compareTo(saldoCheque));
        assertEquals(new BigDecimal("150.00"),this.cc.getChequeEspecial());
    }

    @Test
    public void retirarSaldoComValorNullDeveLancarExcecao() {
        //Asserts
        assertThrows(DadosInvalidosException.class, () -> {
            cc.retirarSaldo(null);
        });
    }

    @Test
    public void retirarSaldoComZeroDeveLancarException() {
        // Asserts
        DadosInvalidosException ex = assertThrows(DadosInvalidosException.class, () -> {
            cc.retirarSaldo(BigDecimal.ZERO);
        });
    }

    @Test
    public void pagarBoletoComValorNegativoDeveLancarExceptionCustom() {
        //Actions
        ExceptionCustom ex = assertThrows(ExceptionCustom.class, () -> {
            cc.pagarBoleto(new BigDecimal("-10.00"));
        });

        //Asserts
        assertInstanceOf(DadosInvalidosException.class, ex.getCause());
    }

    @Test
    public void retirarSaldoEAdicionarSaldo() {
        //Preparation
        BigDecimal saldoAdicionado = new BigDecimal("20.00");
        BigDecimal saldoRetirado = new BigDecimal("210.00");
        BigDecimal saldoVerificador = new BigDecimal("10.00");
        BigDecimal chequeVerificador = new BigDecimal("200.00");

        //Actions
        cc.retirarSaldo(saldoRetirado);
        cc.adicionarSaldo(saldoAdicionado);

        //Asserts
        assertEquals(saldoVerificador, cc.getSaldo());
        assertEquals(chequeVerificador, cc.getChequeEspecial());
    }

}
