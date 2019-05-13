package com.alphadevelopmentsolutions.frcscout.Classes.Tables;

import com.alphadevelopmentsolutions.frcscout.Classes.Database;

public class Robot extends Table
{
    public static final String TABLE_NAME = "robots";
    public static final String COLUMN_NAME_ID = "Id";
    public static final String COLUMN_NAME_NAME = "Name";
    public static final String COLUMN_NAME_TEAM_NUMBER = "TeamId";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +" (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_NAME + " TEXT," +
                    COLUMN_NAME_TEAM_NUMBER + " INTEGER)";

    private int id;
    private String name;
    private int teamNumber;

    public Robot(
            int id,
            String name,
            int teamNumber)
    {
        this.id = id;
        this.name = name;
        this.teamNumber = teamNumber;
    }

    /**
     * Used for loading
     * @param id to load
     */
    Robot(int id)
    {
        this.id = id;
    }

    //region Getters

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getTeamNumber()
    {
        return teamNumber;
    }

    @Override
    public String toString()
    {
        return getName();
    }

    //endregion

    //region Setters

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTeamNumber(int teamNumber)
    {
        this.teamNumber = teamNumber;
    }

    //endregion

    //region Load, Save & Delete

    /**
     * Loads the robot from the database and populates all values
     * @param database used for interacting with the SQLITE db
     * @return boolean if successful
     */
    public boolean load(Database database)
    {
        //try to open the DB if it is not open
        if(!database.isOpen()) database.open();

        if(database.isOpen())
        {
            Robot robot = database.getRobot(this);


            if (robot != null)
            {
                setName(robot.getName());
                setTeamNumber(robot.getTeamNumber());
                return true;
            }
        }

        return false;
    }

    /**
     * Saves the robot into the database
     * @param database used for interacting with the SQLITE db
     * @return boolean if successful
     */
    public int save(Database database)
    {
        int id = -1;

        //try to open the DB if it is not open
        if(!database.isOpen())
            database.open();

        if(database.isOpen())
            id = (int) database.setRobot(this);

        //set the id if the save was successful
        if(id > 0)
            setId(id);

        return getId();
    }

    /**
     * Deletes robot team from the database
     * @param database used for interacting with the SQLITE db
     * @return boolean if successful
     */
    public boolean delete(Database database)
    {
        boolean successful = false;

        //try to open the DB if it is not open
        if(!database.isOpen()) database.open();

        if(database.isOpen())
        {
            successful = database.deleteRobot(this);

        }

        return successful;
    }

    /**
     * Clears all data from the classes table
     * @param database used to clear table
     */
    public static void clearTable(Database database)
    {
        database.clearTable(TABLE_NAME);
    }

    //endregion
}