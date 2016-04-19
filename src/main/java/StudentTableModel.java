import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 4/18/2016.
 */
public class StudentTableModel extends DefaultTableModel {
    private static final String[] columnNames = new String[]{"Last Name", "First Name", "Username", "CUID", "Number of Absences", "Number of Tardies"};

    private List<Student> cachedSearchList = new ArrayList<>();
    private int classId = 0;
    private String term = "";

    public StudentTableModel() {
        super();
    }

    public void setClass(int classId) {
        this.classId = classId;
        update();
    }

    public void setSearchTerm(String searchTerm) {
        this.term = searchTerm.toLowerCase();
        cachedSearchList.clear();
        for (Student s : ClassDataStore.getInstance().getClasses().get(classId).getStudents()) {
            if (s.getCUID().toLowerCase().contains(term)
                    || s.getLastname().toLowerCase().contains(term)
                    || s.getFirstname().toLowerCase().contains(term)
                    || s.getUsername().toLowerCase().contains(term))
                cachedSearchList.add(s);
        }
        fireTableDataChanged();
    }

    public void update() {
        setSearchTerm(this.term);
    }


    /**
     * Returns the number of rows in this data table.
     *
     * @return the number of rows in the model
     */
    @Override
    public int getRowCount() {
        if (cachedSearchList == null)
            return 1;
        return cachedSearchList.size();
    }

    /**
     * Returns the number of columns in this data table.
     *
     * @return the number of columns in the model
     */
    @Override
    public int getColumnCount() {
        return 6;
    }

    /**
     * Returns the column name.
     *
     * @param column
     * @return a name for this column using the string value of the
     * appropriate member in <code>columnIdentifiers</code>.
     * If <code>columnIdentifiers</code> does not have an entry
     * for this index, returns the default
     * name provided by the superclass.
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Returns true regardless of parameter values.
     *
     * @param row    the row whose value is to be queried
     * @param column the column whose value is to be queried
     * @return true
     * @see #setValueAt
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * Returns an attribute value for the cell at <code>row</code>
     * and <code>column</code>.
     *
     * @param row    the row whose value is to be queried
     * @param column the column whose value is to be queried
     * @return the value Object at the specified cell
     * @throws ArrayIndexOutOfBoundsException if an invalid row or
     *                                        column was given
     */
    @Override
    public Object getValueAt(int row, int column) {
        Student s = cachedSearchList.get(row);
        if (s == null) return null;
        switch (column) {
            case 0:
                return s.getLastname();
            case 1:
                return s.getFirstname();
            case 2:
                return s.getUsername();
            case 3:
                return s.getCUID();
            case 4:
                return s.getNumAbsences();
            case 5:
                return s.getNumTardies();
        }
        return null;
    }
}
