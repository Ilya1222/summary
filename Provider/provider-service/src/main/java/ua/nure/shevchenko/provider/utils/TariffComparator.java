package ua.nure.shevchenko.provider.utils;

import ua.nure.shevchenko.provider.entity.Tariff;

import java.util.Comparator;

public class TariffComparator {

    public static Comparator<Tariff> getComparatorByPrice(){
        return Comparator.comparingDouble(Tariff::getPrice);
    }

    public static Comparator<Tariff> getComparatorByService(){
        return Comparator.comparingInt(Tariff::getServiceId);
    }

    public static Comparator<Tariff> getComparatorByName(){
        return ((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }


    public static Comparator<Tariff> getComparatorByNameReverse(){
        return ((o1, o2) -> -o1.getName().compareToIgnoreCase(o2.getName()));
    }




}
