package com.alphadevelopmentsolutions.frcscout.Classes.Tables;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alphadevelopmentsolutions.frcscout.Classes.Database;
import com.alphadevelopmentsolutions.frcscout.Enums.AllianceColor;

import java.util.ArrayList;
import java.util.Date;

public class Match extends Table
{

    public static final String TABLE_NAME = "matches";
    public static final String COLUMN_NAME_ID = "Id";
    public static final String COLUMN_NAME_DATE = "Date";
    public static final String COLUMN_NAME_EVENT_ID = "EventId";
    public static final String COLUMN_NAME_KEY = "Key";
    public static final String COLUMN_NAME_MATCH_TYPE = "MatchType";
    public static final String COLUMN_NAME_MATCH_NUMBER = "MatchNumber";
    public static final String COLUMN_NAME_SET_NUMBER = "SetNumber";
    public static final String COLUMN_NAME_BLUE_ALLIANCE_TEAM_ONE_ID = "BlueAllianceTeamOneId";
    public static final String COLUMN_NAME_BLUE_ALLIANCE_TEAM_TWO_ID = "BlueAllianceTeamTwoId";
    public static final String COLUMN_NAME_BLUE_ALLIANCE_TEAM_THREE_ID = "BlueAllianceTeamThreeId";
    public static final String COLUMN_NAME_RED_ALLIANCE_TEAM_ONE_ID = "RedAllianceTeamOneId";
    public static final String COLUMN_NAME_RED_ALLIANCE_TEAM_TWO_ID = "RedAllianceTeamTwoId";
    public static final String COLUMN_NAME_RED_ALLIANCE_TEAM_THREE_ID = "RedAllianceTeamThreeId";
    public static final String COLUMN_NAME_BLUE_ALLIANCE_SCORE = "BlueAllianceScore";
    public static final String COLUMN_NAME_RED_ALLIANCE_SCORE = "RedAllianceScore";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +" (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_DATE + " INTEGER," +
                    COLUMN_NAME_EVENT_ID + " TEXT," +
                    "\"" + COLUMN_NAME_KEY + "\" TEXT," +
                    COLUMN_NAME_MATCH_TYPE + " TEXT," +
                    COLUMN_NAME_SET_NUMBER + " INTEGER," +
                    COLUMN_NAME_MATCH_NUMBER + " INTEGER," +
                    COLUMN_NAME_BLUE_ALLIANCE_TEAM_ONE_ID + " INTEGER," +
                    COLUMN_NAME_BLUE_ALLIANCE_TEAM_THREE_ID + " INTEGER," +
                    COLUMN_NAME_BLUE_ALLIANCE_TEAM_TWO_ID + " INTEGER," +
                    COLUMN_NAME_BLUE_ALLIANCE_SCORE + " INTEGER," +
                    COLUMN_NAME_RED_ALLIANCE_SCORE + " INTEGER," +
                    COLUMN_NAME_RED_ALLIANCE_TEAM_ONE_ID + " INTEGER," +
                    COLUMN_NAME_RED_ALLIANCE_TEAM_TWO_ID + " INTEGER," +
                    COLUMN_NAME_RED_ALLIANCE_TEAM_THREE_ID + " INTEGER)";

    /**
     * This references the types defined by the blue alliance API
     * When assigning a type, use the TypeReference interface for a more
     * plain english aproach
     */
    public enum Type
    {
        qm,
        qf,
        sf,
        f;

        /**
         * Converts a string value to enum
         * @param matchType string enum name
         * @return enum converted from string
         */
        public static Type getTypeFromString(String matchType)
        {
            if(matchType.toLowerCase().equals(Type.qm.name().toLowerCase()))
                return Type.qm;
            if(matchType.toLowerCase().equals(Type.qf.name().toLowerCase()))
                return Type.qf;
            if(matchType.toLowerCase().equals(Type.sf.name().toLowerCase()))
                return Type.sf;
            else
                return Type.f;
        }

        public String toString(Match match)
        {
            switch (this)
            {
                case qm:
                    return "Quals " + match.getMatchNumber();

                case qf:
                    return "Quarters " + match.getSetNumber() + " Match " + match.getMatchNumber();

                case sf:
                    return "Semis " + match.getSetNumber() + " Match " + match.getMatchNumber();

                case f:
                    return "Finals " + match.getMatchNumber();

                default:
                    return "";
            }
        }
    }

    public interface TypeReference
    {
        Type QUALIFICATIONS = Type.qm;
        Type QUARTER_FINALS = Type.qf;
        Type SEMI_FINALS = Type.sf;
        Type FINALS = Type.f;
    }

    public enum Status
    {
        TIE,
        BLUE,
        RED
    }

    private int id;
    private Date date;
    private String eventId;
    private String key;
    private Type matchType;
    private int setNumber;
    private int matchNumber;
    private int blueAllianceTeamOneId;
    private int blueAllianceTeamTwoId;
    private int blueAllianceTeamThreeId;
    private int blueAllianceScore;
    private int redAllianceScore;
    private int redAllianceTeamOneId;
    private int redAllianceTeamTwoId;
    private int redAllianceTeamThreeId;


    public Match(
            int id,
            Date date,
            String eventId,
            String key,
            Type matchType,
            int setNumber,
            int matchNumber,

            int blueAllianceTeamOneId,
            int blueAllianceTeamTwoId,
            int blueAllianceTeamThreeId,

            int redAllianceTeamOneId,
            int redAllianceTeamTwoId,
            int redAllianceTeamThreeId,

            int blueAllianceScore,
            int redAllianceScore)
    {
        this.id = id;
        this.date = date;
        this.eventId = eventId;
        this.key = key;
        this.matchType = matchType;
        this.setNumber = setNumber;
        this.matchNumber = matchNumber;

        this.blueAllianceTeamOneId = blueAllianceTeamOneId;
        this.blueAllianceTeamTwoId = blueAllianceTeamTwoId;
        this.blueAllianceTeamThreeId = blueAllianceTeamThreeId;

        this.redAllianceTeamOneId = redAllianceTeamOneId;
        this.redAllianceTeamTwoId = redAllianceTeamTwoId;
        this.redAllianceTeamThreeId = redAllianceTeamThreeId;

        this.blueAllianceScore = blueAllianceScore;
        this.redAllianceScore = redAllianceScore;
    }

    /**
     * Used for loading
     * @param id to load
     */
    Match(int id)
    {
        this.id = id;
    }

    //region Getters

    public int getId()
    {
        return id;
    }

    public Date getDate()
    {
        return date;
    }

    public String getEventId()
    {
        return eventId;
    }

    public String getKey()
    {
        return key;
    }

    public Type getMatchType()
    {
        return matchType;
    }

    public int getMatchNumber()
    {
        return matchNumber;
    }

    public int getSetNumber()
    {
        return setNumber;
    }

    public int getBlueAllianceTeamOneId()
    {
        return blueAllianceTeamOneId;
    }

    public int getBlueAllianceTeamTwoId()
    {
        return blueAllianceTeamTwoId;
    }

    public int getBlueAllianceTeamThreeId()
    {
        return blueAllianceTeamThreeId;
    }

    public int getBlueAllianceScore()
    {
        return blueAllianceScore;
    }

    public int getRedAllianceScore()
    {
        return redAllianceScore;
    }

    public int getRedAllianceTeamOneId()
    {
        return redAllianceTeamOneId;
    }

    public int getRedAllianceTeamTwoId()
    {
        return redAllianceTeamTwoId;
    }

    public int getRedAllianceTeamThreeId()
    {
        return redAllianceTeamThreeId;
    }

    /**
     * Returns either the winning team or tie status from the match
     * @return Status enum
     */
    public Status getMatchStatus()
    {
        if(blueAllianceScore == redAllianceScore)
            return Status.TIE;
        else if (blueAllianceScore > redAllianceScore)
            return Status.BLUE;
        else
            return Status.RED;
    }

    /**
     * Finds the alliance color of the teamnumber sent in the param
     * @param team to find
     * @return AllianceColor color of the alliance
     */
    public AllianceColor getTeamAllianceColor(Team team)
    {
        if(blueAllianceTeamOneId == team.getId() ||
            blueAllianceTeamTwoId == team.getId() ||
            blueAllianceTeamThreeId == team.getId()) return AllianceColor.BLUE;

        else if(redAllianceTeamOneId == team.getId() ||
                redAllianceTeamTwoId == team.getId() ||
                redAllianceTeamThreeId == team.getId()) return AllianceColor.RED;

        //default to blue
        return AllianceColor.NONE;
    }

    /**
     * Gets all scout cards associated with the match
     * @param event if specified, filters scout cards by event id
     * @param team if specified, filters scout cards by team id
     * @param scoutCard if specified, filters scout cards by scoutcard id
     * @param onlyDrafts boolean if you only want drafts
     * @param database used for loading cards
     * @return arraylist of scout cards
     */
    public ArrayList<ScoutCard> getScoutCards(@Nullable Event event, @Nullable Team team, @Nullable ScoutCard scoutCard, boolean onlyDrafts, @NonNull Database database)
    {
        return ScoutCard.getScoutCards(event, this, team, scoutCard, onlyDrafts, database);
    }

    /**
     * Gets all teams associated with the match
     * @param event if specified, filters teams by event id
     * @param team if specified, filters teams by team id
     * @param database used for loading cards
     * @return arraylist of scout cards
     */
    public ArrayList<Team> getTeams(@Nullable Event event, @Nullable Team team, Database database)
    {
        return Team.getTeams(event, this, team, database);
    }

    /**
     * Converts the match object into a string title
     * @return string title of match
     */
    public String toString()
    {
        return getMatchType().toString(this);
    }

    //endregion

    //region Setters

    public void setId(int id)
    {
        this.id = id;
    }

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public void setMatchType(Type matchType)
    {
        this.matchType = matchType;
    }

    public void setMatchNumber(int matchNumber)
    {
        this.matchNumber = matchNumber;
    }

    public void setSetNumber(int setNumber)
    {
        this.setNumber = setNumber;
    }

    public void setBlueAllianceTeamOneId(int blueAllianceTeamOneId)
    {
        this.blueAllianceTeamOneId = blueAllianceTeamOneId;
    }

    public void setBlueAllianceTeamTwoId(int blueAllianceTeamTwoId)
    {
        this.blueAllianceTeamTwoId = blueAllianceTeamTwoId;
    }

    public void setBlueAllianceTeamThreeId(int blueAllianceTeamThreeId)
    {
        this.blueAllianceTeamThreeId = blueAllianceTeamThreeId;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setBlueAllianceScore(int blueAllianceScore)
    {
        this.blueAllianceScore = blueAllianceScore;
    }

    public void setRedAllianceScore(int redAllianceScore)
    {
        this.redAllianceScore = redAllianceScore;
    }

    public void setRedAllianceTeamOneId(int redAllianceTeamOneId)
    {
        this.redAllianceTeamOneId = redAllianceTeamOneId;
    }

    public void setRedAllianceTeamTwoId(int redAllianceTeamTwoId)
    {
        this.redAllianceTeamTwoId = redAllianceTeamTwoId;
    }

    public void setRedAllianceTeamThreeId(int redAllianceTeamThreeId)
    {
        this.redAllianceTeamThreeId = redAllianceTeamThreeId;
    }

    //endregion

    //region Load, Save & Delete

    /**
     * Loads the Match from the database and populates all values
     * @param database used for interacting with the SQLITE db
     * @return boolean if successful
     */
    public boolean load(Database database)
    {
        //try to open the DB if it is not open
        if(!database.isOpen()) database.open();

        if(database.isOpen())
        {
            ArrayList<Match> matches = getMatches(null, this, null, database);
            Match match = (matches.size() > 0 ) ? matches.get(0) : null;

            if (match != null)
            {
                setDate(match.getDate());
                setBlueAllianceTeamOneId(match.getBlueAllianceTeamOneId());
                setBlueAllianceTeamTwoId(match.getBlueAllianceTeamTwoId());
                setBlueAllianceTeamThreeId(match.getBlueAllianceTeamThreeId());
                setDate(match.getDate());
                setEventId(match.getEventId());
                setKey(match.getKey());
                setMatchType(match.getMatchType());
                setSetNumber(match.getSetNumber());
                setMatchNumber(match.getMatchNumber());
                setBlueAllianceScore(match.getBlueAllianceScore());
                setRedAllianceScore(match.getRedAllianceScore());
                setRedAllianceTeamOneId(match.getRedAllianceTeamOneId());
                setRedAllianceTeamTwoId(match.getRedAllianceTeamTwoId());
                setRedAllianceTeamThreeId(match.getRedAllianceTeamThreeId());
                return true;
            }
        }

        return false;
    }

    /**
     * Saves the Match into the database
     * @param database used for interacting with the SQLITE db
     * @return int id of the saved Match
     */
    public int save(Database database)
    {
        int id = -1;

        //try to open the DB if it is not open
        if(!database.isOpen())
            database.open();

        if(database.isOpen())
            id = (int) database.setMatch(this);

        //set the id if the save was successful
        if(id > 0)
            setId(id);

        return getId();
    }

    /**
     * Deletes the Match from the database
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
            successful = database.deleteMatch(this);

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

    /**
     * Returns arraylist of teams with specified filters from database
     * @param event if specified, filters teams by event id
     * @param team if specified, filters teams by team id
     * @param database used to load teams
     * @return arraylist of teams
     */
    public static ArrayList<Match> getMatches(@Nullable Event event, @Nullable Match match, @Nullable Team team, @NonNull Database database)
    {
        return database.getMatches(event, match, team);
    }

    //endregion
}