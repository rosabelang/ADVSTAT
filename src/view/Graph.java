package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph extends javax.swing.JFrame {

    /**
     * Creates new form Graph
     */
    public Graph() {
        initComponents();
    }
    public XYSeriesCollection numericalDataset = new XYSeriesCollection();
    private JFreeChart numericalChart = ChartFactory.createXYLineChart("Numerical Graph", "X-Axis", "Y-Axis", numericalDataset, PlotOrientation.VERTICAL, true, true, false);
    private String polyColumns[] = {"x", "y"};
    public DefaultTableModel ptm = new DefaultTableModel(null, polyColumns);
    private JTable polyTable = new JTable(ptm);

    public void polyAddRow(double x, double y) {
        this.ptm.addRow(new Object[]{x, y});
    }
    private String rootColumns[] = {"x", "y"};
    public DefaultTableModel rtm = new DefaultTableModel(null, rootColumns);
    private JTable rootTable = new JTable(rtm);

    public void rootAddRow(double x, double y) {
        this.rtm.addRow(new Object[]{x, y});
    }

    public void addSliderListener(ChangeListener slider) {
        graphSlider.addChangeListener(slider);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        XYPlot plot = numericalChart.getXYPlot();
        plot.setRangeGridlinePaint(Color.black);
        plot.setBackgroundPaint(Color.WHITE);
        plot.getRenderer().setSeriesPaint(0, Color.black);
        plot.getRenderer().setSeriesStroke(1, new BasicStroke(2.5f));
        plot.getRenderer().setSeriesPaint(1, Color.ORANGE);

        ChartPanel chartPanel = new ChartPanel(numericalChart);
        panelChart = new javax.swing.JPanel();
        graphSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        spPolyPoints = new javax.swing.JScrollPane(polyTable);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelChart.setLayout(new java.awt.BorderLayout());
        panelChart.removeAll();
        panelChart.add(chartPanel, BorderLayout.CENTER);
        panelChart.validate();

        graphSlider.setMajorTickSpacing(5);
        graphSlider.setMinorTickSpacing(1);
        graphSlider.setPaintTicks(true);
        graphSlider.setValue(0);
        panelChart.add(graphSlider, java.awt.BorderLayout.PAGE_END);

        jLabel1.setText("Polynomial Points");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spPolyPoints, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spPolyPoints)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JSlider graphSlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelChart;
    private javax.swing.JScrollPane spPolyPoints;
    // End of variables declaration//GEN-END:variables
}
