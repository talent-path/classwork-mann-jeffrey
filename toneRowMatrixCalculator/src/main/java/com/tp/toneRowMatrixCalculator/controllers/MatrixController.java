package com.tp.toneRowMatrixCalculator.controllers;

import com.tp.toneRowMatrixCalculator.models.Matrix;
import com.tp.toneRowMatrixCalculator.models.ToneRow;
import com.tp.toneRowMatrixCalculator.services.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MatrixController {
    @Autowired
    MatrixService service;

    @GetMapping("/matrix")
    public ResponseEntity getAllMatrices() {
        Map<Integer, Matrix> toReturn;
        try {
            toReturn = service.getAllMatrices();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(toReturn);
    }

}
