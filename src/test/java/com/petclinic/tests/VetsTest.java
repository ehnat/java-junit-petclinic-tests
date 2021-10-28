package com.petclinic.tests;

import com.petclinic.data.dto.Vet;
import com.petclinic.services.VetService;
import com.petclinic.tests.tags.RegressionTest;
import com.petclinic.tests.tags.SmokeParameterizedTest;
import com.petclinic.tests.tags.SmokeTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Vets:")
public class VetsTest {

    private static final int INITIAL_VETS_AMOUNT = 6;

    @RegressionTest
    @SmokeTest
    @DisplayName("should return all vets")
    void shouldReturnAllVets() {
        //when
        var allVets = VetService.getAllVets();

        //then
        assertAll("vets:",
                () -> assertTrue(allVets.size() > 0, () -> "vets amount is greater than 0"),
                () -> assertEquals(INITIAL_VETS_AMOUNT, allVets.size(), () -> "vets amount = 6 (initial value)")
        );
    }

    @SmokeParameterizedTest
    @MethodSource("com.petclinic.tests.DataProvider#getVetDetails")
    @DisplayName("should get a vet:")
    void shouldGetVet(int vetId, String firstName) {
        //when
        Vet vet = VetService.getVet(vetId);

        //then
        assertAll("get a vet:",
                () -> assertEquals(vetId, vet.id(), () -> String.format("id is correct: %s", vetId)),
                () -> assertEquals(firstName, vet.firstName(), () -> String.format("firstName is correct: %s", firstName))
        );
    }
}
