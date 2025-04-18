package edu.eci.bcol.Presentation;


import edu.eci.bcol.Domain.Product;
import edu.eci.bcol.Persistence.ProductRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class ProductForm extends JFrame {
    private JTextField nombreField;
    private JTextField precioField;
    private JTextField cantidadField;
    private ProductRepository repositorio;
    private Color colorPrimario = new Color(255, 223, 0);
    private Color colorSecundario = new Color(0, 40, 85);

    public ProductForm() {
        super("Gestión de Productos");
        repositorio = new ProductRepository();
        prepareElements();
    }

    private void prepareElements() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600); // Tamaño más grande
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 20));
        getContentPane().setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel logo = new JLabel();
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/bancolombia.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        logo.setIcon(scaledIcon);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(logo, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        nombreField = createStyledTextField();
        precioField = createStyledTextField();
        cantidadField = createStyledTextField();

        mainPanel.add(createStyledLabel("Nombre del producto:"));
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(nombreField);
        mainPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(createStyledLabel("Precio:"));
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(precioField);
        mainPanel.add(Box.createVerticalStrut(15));

        mainPanel.add(createStyledLabel("Cantidad en stock:"));
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(cantidadField);

        add(mainPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton guardarBtn = createStyledButton("Guardar");
        guardarBtn.addActionListener(this::saveProducto);
        bottomPanel.add(guardarBtn);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(colorSecundario);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorSecundario),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        return field;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(colorSecundario);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));


        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(colorPrimario);
                button.setForeground(colorSecundario);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(colorSecundario);
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

    private void saveProducto(ActionEvent e) {
        try {
            String nombre = nombreField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());
            int id = new Random().nextInt();
            Product producto = new Product(id, nombre, precio, cantidad);
            repositorio.save(producto);

            JOptionPane.showMessageDialog(
                    this,
                    "Se ha guardado el siguiente producto:\n" + producto.getNombre() + "\nPrecio: " + producto.getPrecio() + "\nCantidad: " + producto.getCantidadStock(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );

            nombreField.setText("");
            precioField.setText("");
            cantidadField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, verifique los datos ingresados:\n- El precio debe ser un número decimal\n- La cantidad debe ser un número entero",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}