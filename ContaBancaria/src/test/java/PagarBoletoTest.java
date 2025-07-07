import br.com.contaBancaria.Controllers.ContaControllers;
import br.com.contaBancaria.Exception.ExceptionCustom;
import br.com.contaBancaria.Exception.InsufficientFundsException;
import br.com.contaBancaria.Models.ContaBancaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PagarBoletoTest {
    ContaControllers cc;

    @BeforeEach
    public void config() {
        ContaBancaria con = new ContaBancaria("NUBANK",new BigDecimal("200"));
        cc = new ContaControllers(con);
    }

    @Test
    public void pagarBoletoSemSaldoInsuficiente() {
        //Preparation
        BigDecimal valorBoleto = new BigDecimal("500");

        //Actions
        ExceptionCustom ex = assertThrows(ExceptionCustom.class, () -> {
            cc.pagarBoleto(valorBoleto);
        });

        //Asserts
        assertInstanceOf(InsufficientFundsException.class, ex.getCause());
    }

    @Test
    public void pagarBoletoComChequeEspecial() {
        //Preparation
        BigDecimal valorBoleto = new BigDecimal("220.00");
        BigDecimal valorChequeEsperado = new BigDecimal("30.00");

        //Actions
        cc.pagarBoleto(valorBoleto);

        //Asserts
        assertTrue(cc.getUsandoChequeEspecial());
        assertEquals(valorChequeEsperado, cc.getSaldoTotal());
        assertEquals(valorChequeEsperado, cc.getChequeEspecial());
    }

    @Test
    public void pagarBoletoComSaldo() {
        //Preparation
        BigDecimal valorBoleto = new BigDecimal("200");

        //Actions
        cc.pagarBoleto(valorBoleto);

        //Asserts
        assertEquals(0, cc.getSaldo().compareTo(BigDecimal.ZERO));
    }
}
