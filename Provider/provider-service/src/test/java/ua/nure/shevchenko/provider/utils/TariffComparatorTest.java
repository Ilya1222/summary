package ua.nure.shevchenko.provider.utils;

import org.junit.Test;
import ua.nure.shevchenko.provider.entity.Tariff;

import java.util.Comparator;

import static org.junit.Assert.*;

public class TariffComparatorTest {

    @Test
    public void getComparatorByPrice() {
        Tariff tariff1 = new Tariff("name","sprec",21.20,1);
        Tariff tariff2 = new Tariff("name1","sprec1",20.20,2);
        Comparator<Tariff> comparator = TariffComparator.getComparatorByPrice();
        int c = comparator.compare(tariff1,tariff2);
        assertEquals(1, c);
    }

    @Test
    public void getComparatorByService() {
        Tariff tariff1 = new Tariff("name","sprec",20.20,1);
        Tariff tariff2 = new Tariff("name1","sprec1",20.20,2);

        Comparator<Tariff> comparator = TariffComparator.getComparatorByService();

        int c = comparator.compare(tariff1,tariff2);

        assertEquals(-1,c);

    }

    @Test
    public void getComparatorByName() {
        Tariff tariff1 = new Tariff("Business","sprec",20.20,1);
        Tariff tariff2 = new Tariff("Vip","sprec1",20.20,2);

        Comparator<Tariff> comparator = TariffComparator.getComparatorByName();

        int c = comparator.compare(tariff1,tariff2);

        assertEquals(-20,c);

    }

    @Test
    public void getComparatorByNameReverse() {

        Tariff tariff1 = new Tariff("Business","sprec",20.20,1);
        Tariff tariff2 = new Tariff("Vip","sprec1",20.20,2);

        Comparator<Tariff> comparator = TariffComparator.getComparatorByNameReverse();

        int c = comparator.compare(tariff1,tariff2);

        assertEquals(20,c);
    }
}