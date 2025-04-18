package edu.eci.bcol;

import edu.eci.bcol.Presentation.ProductForm;

import javax.swing.*;

public class ProductApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductForm().setVisible(true);
        });
    }
}

