package com.aleislan.ticket.machine.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PapelMoedaTest {

    @ParameterizedTest
    @MethodSource("provideParameters")
    void Should_Get_Correct_Values(int valor, int quantidade) {
        PapelMoeda papelMoeda = new PapelMoeda(valor, quantidade);

        assertEquals(valor, papelMoeda.getValor());
        assertEquals(quantidade, papelMoeda.getQuantidade());
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(2, 1),
                Arguments.of(5, 2),
                Arguments.of(10, 3),
                Arguments.of(20, 1),
                Arguments.of(50, 2),
                Arguments.of(100, 3)
        );
    }

}
