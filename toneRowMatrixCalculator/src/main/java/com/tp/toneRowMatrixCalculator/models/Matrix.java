package com.tp.toneRowMatrixCalculator.models;

import java.util.Map;

public class Matrix {
//    public Map<String, Note[]> primes;
//    public Map<String, Note[]> inversions;
//    public Map<String, Note[]> retrogrades;
//    public Map<String, Note[]> retrogradeInversions;
    int[][] matrix;

    public Matrix() {
        matrix = new int[12][12];
    }

//    public Map<String, Note[]> getPrimes() {
//        return primes;
//    }
//
//    public void setPrimes(Map<String, Note[]> primes) {
//        this.primes = primes;
//    }
//
//    public Map<String, Note[]> getInversions() {
//        return inversions;
//    }
//
//    public void setInversions(Map<String, Note[]> inversions) {
//        this.inversions = inversions;
//    }
//
//    public Map<String, Note[]> getRetrogrades() {
//        return retrogrades;
//    }
//
//    public void setRetrogrades(Map<String, Note[]> retrogrades) {
//        this.retrogrades = retrogrades;
//    }
//
//    public Map<String, Note[]> getRetrogradeInversions() {
//        return retrogradeInversions;
//    }
//
//    public void setRetrogradeInversions(Map<String, Note[]> retrogradeInversions) {
//        this.retrogradeInversions = retrogradeInversions;
//    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
