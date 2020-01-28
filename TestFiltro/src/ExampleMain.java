		import javax.swing.*;
		import javax.swing.table.DefaultTableModel;
		import javax.swing.table.TableModel;
		import java.awt.*;
		import java.util.Arrays;
		import java.util.Vector;

		public class ExampleMain {
		    public static void main(String[] args) {
		        JFrame frame = createFrame();
		        TableModel tableModel = createTableModel();
		        JTable table = new JTable(tableModel);

		        JTextField filterField = RowFilterUtil.createRowFilter(table);
		        JPanel jp = new JPanel();
		        jp.add(filterField);
		        frame.add(jp, BorderLayout.NORTH);

		        JScrollPane pane = new JScrollPane(table);
		        frame.add(pane, BorderLayout.CENTER);
		        frame.setLocationRelativeTo(null);
		        frame.setVisible(true);
		    }

		    private static TableModel createTableModel() {
		        Vector<String> columns = new Vector<>(Arrays.asList("Name", "Address", "Age"));
		        Vector<Vector<Object>> rows = new Vector<>();

		        DataFactory dataFactory = new DataFactory();
		        for (int i = 1; i <= 30; i++) {
		            Vector<Object> v = new Vector<>();
		            v.add(dataFactory.getName());
		            v.add(dataFactory.getAddress() + ", " + dataFactory.getCity());
		            v.add(dataFactory.getNumberBetween(18, 80));
		            rows.add(v);
		        }

		        DefaultTableModel dtm = new DefaultTableModel(rows, columns) {
		            @Override
		            public Class<?> getColumnClass(int columnIndex) {
		                return columnIndex == 2 ? Integer.class : String.class;
		            }
		        };
		        return dtm;
		    }

		    private static JFrame createFrame() {
		        JFrame frame = new JFrame("JTable Row filter example");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(new Dimension(600, 450));
		        return frame;
		    }
		}

