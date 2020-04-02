package restaurant.data.repositories;

import restaurant.data.services.Service;
import restaurant.data.services.TableService;
import restaurant.models.reservation.Table;

import java.util.ArrayList;

public class TableRepository {
    private Service<Table> tableService = new TableService();
    private ArrayList<Table> tables;

    public TableRepository()
    {
        populateList();
    }

    private void populateList()
    {
        tables = tableService.readData();
    }

    // READING FUNCTIONS

    public ArrayList<Table> getTables()
    {
        return tables;
    }

    public Table getTableByTableNumber(int tableNumber)
    {
        for (Table table:tables)
        {
            if (table.getTableNumber() == tableNumber)
                return table;
        }

        return null;
    }

    public ArrayList<Table> getTablesByNumberOfSeats(int numberOfSeats)
    {
        ArrayList<Table> _tables = new ArrayList<>();
        for (Table table:tables)
        {
            if (table.getNumberOfSeats() == numberOfSeats)
                _tables.add(table);
        }

        return _tables;
    }

    public ArrayList<Table> getTablesByEligibleNumberOfSeatsAndSmoking(int numberOfSeats, boolean isSmoking)
    {
        ArrayList<Table> _tables = new ArrayList<>();
        for (Table table:tables)
        {
            if (table.getNumberOfSeats() >= numberOfSeats && table.getIsSmoking() == isSmoking)
                _tables.add(table);
        }

        return _tables;
    }

    public ArrayList<Table> getSmokingTables()
    {
        ArrayList<Table> _tables = new ArrayList<>();
        for (Table table:tables)
        {
            if (table.isSmoking())
                _tables.add(table);
        }

        return _tables;
    }

    public ArrayList<Table> getNonSmokingTables()
    {
        ArrayList<Table> _tables = new ArrayList<>();
        for (Table table:tables)
        {
            if (!table.isSmoking())
                _tables.add(table);
        }

        return _tables;
    }
}
