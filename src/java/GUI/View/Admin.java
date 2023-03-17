package GUI.View;

import javax.swing.*;
import java.awt.*;

public class Admin extends JFrame {
    private JFrame frame = this;
    private JPanel panel;

    private JTable table;
    private JButton add;
    private JButton delete;
    private JButton edit;
    private JButton back;
    private JButton generateReport;
    private JButton importProducts;
    private JScrollPane scrollPane;
    private JButton compositeProduct;

    public Admin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("Admin");
        setLocationRelativeTo(null);
        table = new JTable();
        table.setBounds(25, 50, 750, 400);
        add = new JButton("Add");
        edit = new JButton("Edit");
        delete = new JButton("Delete");
        back = new JButton("Back");
        generateReport = new JButton("Generate Report");
        importProducts = new JButton("Import Products");
        compositeProduct = new JButton("Composite Product");
        scrollPane = new JScrollPane(table);


        panel = new JPanel();
        panel.add(table);
        panel.add(add);
        panel.add(edit);
        panel.add(delete);
        panel.add(compositeProduct);
        panel.add(generateReport);
        panel.add(importProducts);
        panel.add(back);
        panel.add(scrollPane);

        add(panel);

        setVisible(true);
    }

    //region get&set


    public JButton getCompositeProduct() {
        return compositeProduct;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JButton getImportProducts() {
        return importProducts;
    }

    public void setImportProducts(JButton importProducts) {
        this.importProducts = importProducts;
    }

    public JButton getGenerateReport() {
        return generateReport;
    }

    public void setGenerateReport(JButton generateReport) {
        this.generateReport = generateReport;
    }

    public JButton getBack() {
        return back;
    }

    public void setBack(JButton back) {
        this.back = back;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JButton getAdd() {
        return add;
    }

    public void setAdd(JButton add) {
        this.add = add;
    }

    public JButton getEdit() {
        return edit;
    }

    public void setEdit(JButton edit) {
        this.edit = edit;
    }

    public JButton getDelete() {
        return delete;
    }

    public void setDelete(JButton delete) {
        this.delete = delete;
    }

    //endregion
}
