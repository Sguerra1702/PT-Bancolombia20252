package edu.eci.bcol.Presentation;


import edu.eci.bcol.Domain.Product;
import edu.eci.bcol.Persistence.ProductRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.*;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.poifs.crypt.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Botón Guardar
        JButton guardarBtn = createStyledButton("Guardar");
        guardarBtn.addActionListener(this::saveProducto);

        // Nuevo botón para generar Excel
        JButton excelBtn = createStyledButton("Generar Excel");
        excelBtn.addActionListener(this::generateExcel);

        bottomPanel.add(guardarBtn);
        bottomPanel.add(excelBtn);

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
            int id = Math.abs(new Random().nextInt());
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

    private void generateExcel(ActionEvent e) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Productos");
            String password = "Bancolombia2025";

            // Estilos del encabezado
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // Crear encabezados
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Nombre", "Precio", "Cantidad", "Fecha Registro"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Añadir datos
            List<Product> productos = repositorio.getAll();
            int rowNum = 1;
            for (Product producto : productos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(producto.getId());
                row.createCell(1).setCellValue(producto.getNombre());
                row.createCell(2).setCellValue(producto.getPrecio());
                row.createCell(3).setCellValue(producto.getCantidadStock());
                row.createCell(4).setCellValue(
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                );
            }

            // Ajustar columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Proteger la hoja
            sheet.protectSheet(password);

            // Nombre del archivo
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = "Productos_" + timestamp + ".xlsx";

            // Guardar el archivo
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                // Crear el paquete temporal
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                workbook.write(bos);

                // Configurar la encriptación
                POIFSFileSystem fs = new POIFSFileSystem();
                EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
                Encryptor enc = info.getEncryptor();
                enc.confirmPassword(password);

                // Encriptar y guardar
                try (OutputStream os = enc.getDataStream(fs)) {
                    os.write(bos.toByteArray());
                }
                fs.writeFilesystem(fileOut);
            }

            workbook.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Archivo Excel generado exitosamente:\n" +
                            "Nombre: " + fileName + "\n" +
                            "Contraseña: " + password + "\n" +
                            "Total de productos: " + (rowNum - 1),
                    "Excel Generado",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al generar el archivo Excel:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}