package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author trotu
 * @param <T>
 */
// La Interfaz del patron prototype
public interface Clonable<T> extends Cloneable {
    T clonar();
}