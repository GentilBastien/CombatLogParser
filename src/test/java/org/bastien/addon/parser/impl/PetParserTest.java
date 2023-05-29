package org.bastien.addon.parser.impl;

import org.bastien.addon.model.entities.Pet;
import org.bastien.addon.parser.impl.PetParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetParserTest {

    @Test
    public void parseNormalPet() {
        PetParser parser = new PetParser();
        Pet actual = parser.parse("[@Jiñdsõ#690019855982325/Treek {3754407007092736}:12381008606353|(894.33,4.30,192.82,91.28)|(295328/295328)]");
        Pet expected = Pet.builder().id(3754407007092736L).idType(12381008606353L).name("Treek").idOwner(690019855982325L).nameOwner("Jiñdsõ").positionX(894.33).positionY(4.30).positionZ(192.82).angle(91.28).health(295328L).maxHealth(295328L).build();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getIdType(), actual.getIdType());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getIdOwner(), actual.getIdOwner());
        assertEquals(expected.getNameOwner(), actual.getNameOwner());
        assertEquals(expected.getPositionX(), actual.getPositionX());
        assertEquals(expected.getPositionY(), actual.getPositionY());
        assertEquals(expected.getPositionZ(), actual.getPositionZ());
        assertEquals(expected.getAngle(), actual.getAngle());
        assertEquals(expected.getHealth(), actual.getHealth());
        assertEquals(expected.getMaxHealth(), actual.getMaxHealth());
    }

    @Test
    public void parseNormalPetButOtherPlayer() {
        PetParser parser = new PetParser();
        Pet actual = parser.parse("[@Shãlom#0/Tre'  eko {3754407007092736}:12381008606353|(898.91,2.46,192.82,115.65)|(405788/405788)]");
        Pet expected = Pet.builder().id(3754407007092736L).idType(12381008606353L).name("Tre'  eko").idOwner(0).nameOwner("Shãlom").positionX(898.91).positionY(2.46).positionZ(192.82).angle(115.65).health(405788).maxHealth(405788).build();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getIdType(), actual.getIdType());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getIdOwner(), actual.getIdOwner());
        assertEquals(expected.getNameOwner(), actual.getNameOwner());
        assertEquals(expected.getPositionX(), actual.getPositionX());
        assertEquals(expected.getPositionY(), actual.getPositionY());
        assertEquals(expected.getPositionZ(), actual.getPositionZ());
        assertEquals(expected.getAngle(), actual.getAngle());
        assertEquals(expected.getHealth(), actual.getHealth());
        assertEquals(expected.getMaxHealth(), actual.getMaxHealth());
    }

    @Test
    public void parsePetUnknownPlayer() {
        PetParser parser = new PetParser();
        Pet actual = parser.parse("[@UNKNOWN/Kira Carsen {3604151871209472}:175000134170|(366.58,-822.87,-201.21,-68.59)|(489/168522)]");
        Pet expected = Pet.builder().id(3604151871209472L).idType(175000134170L).name("Kira Carsen").idOwner(-1).nameOwner("UNKNOWN").positionX(366.58).positionY(-822.87).positionZ(-201.21).angle(-68.59).health(489).maxHealth(168522).build();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getIdType(), actual.getIdType());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getIdOwner(), actual.getIdOwner());
        assertEquals(expected.getNameOwner(), actual.getNameOwner());
        assertEquals(expected.getPositionX(), actual.getPositionX());
        assertEquals(expected.getPositionY(), actual.getPositionY());
        assertEquals(expected.getPositionZ(), actual.getPositionZ());
        assertEquals(expected.getAngle(), actual.getAngle());
        assertEquals(expected.getHealth(), actual.getHealth());
        assertEquals(expected.getMaxHealth(), actual.getMaxHealth());
    }
}
