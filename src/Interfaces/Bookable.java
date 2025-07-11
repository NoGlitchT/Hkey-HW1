package Interfaces;

import java.time.LocalDate;

import java.util.List;


public interface Bookable <T> {
    boolean isAvailable(List<T> bookables, LocalDate start, LocalDate end) throws Exception;

}
