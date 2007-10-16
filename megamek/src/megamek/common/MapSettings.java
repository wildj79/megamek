/*
 * MegaMek - Copyright (C) 2002,2003 Ben Mazur (bmazur@sev.org)
 * 
 *  This program is free software; you can redistribute it and/or modify it 
 *  under the terms of the GNU General Public License as published by the Free 
 *  Software Foundation; either version 2 of the License, or (at your option) 
 *  any later version.
 * 
 *  This program is distributed in the hope that it will be useful, but 
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 *  for more details.
 */

package megamek.common;

import gd.xml.ParseException;
import gd.xml.tiny.ParsedXML;
import gd.xml.tiny.TinyParser;

import java.util.*;
import java.io.*;

import megamek.common.util.BuildingTemplate;

/**
 *
 * MapSettings.java
 *
 * Created on March 27, 2002, 1:07 PM
 * @author  Ben
 */
public class MapSettings implements Serializable {
	private static final long serialVersionUID = 6838624193286089780L;
	public static final String BOARD_RANDOM = "[RANDOM]";
    public static final String BOARD_SURPRISE = "[SURPRISE]";
    public static final String BOARD_GENERATED = "[GENERATED]";    
    
    public static final int MOUNTAIN_PLAIN = 0;
    public static final int MOUNTAIN_VOLCANO_EXTINCT = 1;
    public static final int MOUNTAIN_VOLCANO_DORMANT = 2;
    public static final int MOUNTAIN_VOLCANO_ACTIVE = 3;
    public static final int MOUNTAIN_SNOWCAPPED = 4;
    public static final int MOUNTAIN_LAKE = 5;
    
    private int boardWidth = 16;
    private int boardHeight = 17;
    private int mapWidth = 1;
    private int mapHeight = 1;
    
    // FIXME these vectors get both Board and Strings assinged to them. most likely a bug, at the very least obfuscated code.
    private Vector<String> boardsSelected = new Vector<String>();
    private Vector<String> boardsAvailable = new Vector<String>();

    //new vector to store all of the mapsetting buildings in.
    private Vector<BuildingTemplate> boardBuildings = new Vector<BuildingTemplate>();
    
     /** Parameters for the Map Generator 
         Parameters refer to a default map siz 16 x 17, with other size
         some of the parameters get linear transformed to give good
         result for new size */
    
    /** how much hills there should be, Range 0..99 */
    private int hilliness = 40;
    /** how much cliffs should there be, range 0-100 (% chance for each cliff candidate)*/
    private int cliffs = 0;
    /** Maximum difference between highest elevation and lowest sink */
    private int range = 5;
    /** Probabiltity for invertion of the map, Range 0..100 */
    private int probInvert = 5;
    
    /** how much Lakes at least */
    private int minWaterSpots = 1;
    /** how much Lakes at most */
    private int maxWaterSpots = 3;
    /** minimum size of a lake */
    private int minWaterSize = 5;
    /** maximum Size of a lake */
    private int maxWaterSize = 10;
    /** probability for water deeper than lvl1, Range 0..100 */
    private int probDeep = 33;
    
    /** how much forests at least */
    private int minForestSpots = 3;
    /** how much forests at most */
    private int maxForestSpots = 8;
    /** minimum size of a forest */
    private int minForestSize = 4;
    /** maximum Size of a forest */
    private int maxForestSize = 12;
    /** probability for heavy woods, Range 0..100 */
    private int probHeavy = 30;
    
    /** how much rough spots at least */
    private int minRoughSpots = 2;
    /** how much rough spots  at most */
    private int maxRoughSpots = 10;
    /** minimum size of a rough spot */
    private int minRoughSize = 1;
    /** maximum Size of a rough spot */
    private int maxRoughSize = 2;
    
    /** how much swamp spots at least */
    private int minSwampSpots = 2;
    /** how much swamp spots  at most */
    private int maxSwampSpots = 10;
    /** minimum size of a swamp spot */
    private int minSwampSize = 1;
    /** maximum Size of a swamp spot */
    private int maxSwampSize = 2;
    
    /** how much pavement spots at least */
    private int minPavementSpots = 0;
    /** how much pavement spots  at most */
    private int maxPavementSpots = 0;
    /** minimum size of a pavement spot */
    private int minPavementSize = 1;
    /** maximum Size of a pavement spot */
    private int maxPavementSize = 6;
    
    /** how much rubble spots at least */
    private int minRubbleSpots = 0;
    /** how much rubble spots  at most */
    private int maxRubbleSpots = 0;
    /** minimum size of a rubble spot */
    private int minRubbleSize = 1;
    /** maximum Size of a rubble spot */
    private int maxRubbleSize = 6;
    
    /** how much fortified spots at least */
    private int minFortifiedSpots = 0;
    /** how much fortified spots  at most */
    private int maxFortifiedSpots = 0;
    /** minimum size of a fortified spot */
    private int minFortifiedSize = 1;
    /** maximum Size of a fortified spot */
    private int maxFortifiedSize = 2;
    
    /** how much ice spots at least */
    private int minIceSpots = 0;
    /** how much ice spots  at most */
    private int maxIceSpots = 0;
    /** minimum size of a ice spot */
    private int minIceSize = 1;
    /** maximum Size of a ice spot */
    private int maxIceSize = 6;
    
    /** probability for a road, range 0..100 */
    private int probRoad = 0;
    
    /** probability for a river, range 0..100 */
    private int probRiver = 0;
    
    /** probabilitay for Crater 0..100 */
    private int probCrater = 0;
    
    /** minimum Radius of the Craters */
    private int minRadius = 2;
    
    /** maximum Radius of the Craters */
    private int maxRadius = 7;
    
    /** maximum Number of Craters on one map */
    private int maxCraters = 2;
    
    /** minimum Number of Craters on one map */
    private int minCraters = 1;
    
    /** which landscape generation Algortihm to use */
    /* atm there are 2 different: 0= first, 1=second */
    private int algorithmToUse = 0;
    
    /** a tileset theme to apply */
    private String theme = "";
    
    /** probability of flooded map */
    private int probFlood = 0;
    /** probability of forest fire */
    private int probForestFire = 0;
    /** probability of frozen map */
    private int probFreeze = 0;
    /** probability of drought */
    private int probDrought = 0;
    /** special FX modifier */
    private int fxMod = 0;

    /** Parameters for the city generator */
    private int cityBlocks = 16;
    private String cityType = "NONE";
    private int cityMinCF = 10;
    private int cityMaxCF = 100;
    private int cityMinFloors = 1;
    private int cityMaxFloors = 6;
    private int cityDensity = 75;
    private int townSize = 60;
    
    private int invertNegativeTerrain = 0;
    
    private int mountainPeaks = 0;
    private int mountainWidthMin = 7;
    private int mountainWidthMax = 20;
    private int mountainHeightMin = 5;
    private int mountainHeightMax = 8;
    private int mountainStyle = MOUNTAIN_PLAIN;
    
    /** end Map Generator Parameters */

    /** Creates new MapSettings */
    public MapSettings() {
        this(megamek.common.preference.PreferenceManager.getClientPreferences().getBoardWidth(),
        		megamek.common.preference.PreferenceManager.getClientPreferences().getBoardHeight(),
        		megamek.common.preference.PreferenceManager.getClientPreferences().getMapWidth(),
        		megamek.common.preference.PreferenceManager.getClientPreferences().getMapHeight());
    }
    
    /** Create new MapSettings with all size settings specified */
    public MapSettings(int boardWidth, int boardHeight, int mapWidth, int mapHeight) {
        setBoardSize(boardWidth, boardHeight);
        setMapSize(mapWidth, mapHeight);
    }
    
    /** Creates new MapSettings that is a duplicate of another */
    @SuppressWarnings("unchecked")
	public MapSettings(MapSettings other) {
        this.boardWidth = other.getBoardWidth();
        this.boardHeight = other.getBoardHeight();
        this.mapWidth = other.getMapWidth();
        this.mapHeight = other.getMapHeight();
        
        this.boardsSelected = (Vector<String>)other.getBoardsSelectedVector().clone();
        this.boardsAvailable = (Vector<String>)other.getBoardsAvailableVector().clone();

        this.invertNegativeTerrain = other.getInvertNegativeTerrain();
        this.mountainHeightMin = other.getMountainHeightMin();
        this.mountainHeightMax = other.getMountainHeightMax();
        this.mountainPeaks = other.getMountainPeaks();
        this.mountainStyle = other.getMountainStyle();
        this.mountainWidthMin = other.getMountainWidthMin();
        this.mountainWidthMax = other.getMountainWidthMax();
        this.hilliness = other.getHilliness();
        this.cliffs = other.getCliffs();
        this.range = other.getRange();
        this.probInvert = other.getProbInvert();
        this.minWaterSpots = other.getMinWaterSpots();
        this.maxWaterSpots = other.getMaxWaterSpots();
        this.minWaterSize = other.getMinWaterSize();
        this.maxWaterSize = other.getMaxWaterSize();
        this.probDeep = other.getProbDeep();
        this.minForestSpots = other.getMinForestSpots();
        this.maxForestSpots = other.getMaxForestSpots();
        this.minForestSize = other.getMinForestSize();
        this.maxForestSize = other.getMaxForestSize();
        this.probHeavy = other.getProbHeavy();
        this.minRoughSpots = other.getMinRoughSpots();
        this.maxRoughSpots = other.getMaxRoughSpots();
        this.minRoughSize = other.getMinRoughSize();
        this.maxRoughSize = other.getMaxRoughSize();
        this.minSwampSpots = other.getMinSwampSpots();
        this.maxSwampSpots = other.getMaxSwampSpots();
        this.minSwampSize = other.getMinSwampSize();
        this.maxSwampSize = other.getMaxSwampSize();
        this.minPavementSpots = other.getMinPavementSpots();
        this.maxPavementSpots = other.getMaxPavementSpots();
        this.minPavementSize = other.getMinPavementSize();
        this.maxPavementSize = other.getMaxPavementSize();
        this.minRubbleSpots = other.getMinRubbleSpots();
        this.maxRubbleSpots = other.getMaxRubbleSpots();
        this.minRubbleSize = other.getMinRubbleSize();
        this.maxRubbleSize = other.getMaxRubbleSize();
        this.minFortifiedSpots = other.getMinFortifiedSpots();
        this.maxFortifiedSpots = other.getMaxFortifiedSpots();
        this.minFortifiedSize = other.getMinFortifiedSize();
        this.maxFortifiedSize = other.getMaxFortifiedSize();
        this.minIceSpots = other.getMinIceSpots();
        this.maxIceSpots = other.getMaxIceSpots();
        this.minIceSize = other.getMinIceSize();
        this.maxIceSize = other.getMaxIceSize();
        this.probRoad = other.getProbRoad();
        this.probRiver = other.getProbRiver();
        this.probCrater = other.getProbCrater();
        this.minRadius = other.getMinRadius();
        this.maxRadius = other.getMaxRadius();
        this.minCraters = other.getMinCraters();
        this.maxCraters = other.getMaxCraters();
        this.algorithmToUse = other.getAlgorithmToUse();
        this.theme = other.getTheme();
        this.probFlood = other.getProbFlood();
        this.probForestFire = other.getProbForestFire();
        this.probFreeze = other.getProbFreeze();
        this.probDrought = other.getProbDrought();
        this.fxMod = other.getFxMod();
        this.cityBlocks = other.getCityBlocks();
        this.cityType = other.getCityType();
        this.cityMinCF = other.getCityMinCF();
        this.cityMaxCF = other.getCityMaxCF();
        this.cityMinFloors = other.getCityMinFloors();
        this.cityMaxFloors = other.getCityMaxFloors();
        this.cityDensity = other.getCityDensity();
        this.boardBuildings = other.getBoardBuildings();
    }
    
    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
    
    public void setBoardSize(int boardWidth, int boardHeight) {
        if (boardWidth <= 0 || boardHeight <= 0) {
            throw new IllegalArgumentException("Total board area must be positive");
        }
        
        // change only if actually different
        if (this.boardWidth != boardWidth || this.boardHeight != boardHeight) {
            this.boardWidth = boardWidth;
            this.boardHeight = boardHeight;

            boardsAvailable.removeAllElements();
        }
    }

    public String getTheme() {
        return theme;
    }
    
    public void setTheme(String th) {
        theme = th;
    }
    
    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapSize(int mapWidth, int mapHeight) {
        if (mapWidth <= 0 || mapHeight <= 0) {
            throw new IllegalArgumentException("Total map area must be positive");
        }
        
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        
        boardsSelected.setSize(mapWidth * mapHeight);
    }
    
    public Enumeration<String> getBoardsSelected() {
        return boardsSelected.elements();
    }

    public Vector<String> getBoardsSelectedVector() {
        return boardsSelected;
    }
    
    public void setBoardsSelectedVector(Vector<String> boardsSelected) {
        this.boardsSelected = boardsSelected;
    }
    
    /**
     * Fills in all nulls in the boards selected list with the specified board
     */
    public void setNullBoards(String board) {
        for (int i = 0; i < boardsSelected.size(); i++) {
            if (boardsSelected.elementAt(i) == null) {
                boardsSelected.setElementAt(board, i);
            }
        }
    }
    
    public Vector<BuildingTemplate> getBoardBuildings(){
        return boardBuildings;
    }
    
    public void setBoardBuildings(Vector<BuildingTemplate> buildings) {
        this.boardBuildings = buildings;
    }

    /**
     * Replaces the specified type of board with random boards
     */
    public void replaceBoardWithRandom(String board) {
        for (int i = 0; i < boardsSelected.size(); i++) {
            if (boardsSelected.elementAt(i).equals(board)) {
                int rindex;
                //if we have no boards, set rindex to 0, so the generated board
                //gets selected
                if (boardsAvailable.size() == 1)
                    rindex = 0;
                else rindex = Compute.randomInt(boardsAvailable.size() - 3) + 3;
                // Do a one pi rotation half of the time.
                if ( 0 == Compute.randomInt(2) ) {
                    boardsSelected.setElementAt
                        (Board.BOARD_REQUEST_ROTATION +
                         boardsAvailable.elementAt(rindex), i);
                } else {
                    boardsSelected.setElementAt
                        (boardsAvailable.elementAt(rindex), i);
                }
            }
        }
    }
    
    /**
     * Removes selected boards that aren't listed in the available boards
     */
    public void removeUnavailable() {
        for (int i = 0; i < boardsSelected.size(); i++) {
            if (boardsSelected.elementAt(i) == null || boardsAvailable.size() == 0 
            || boardsAvailable.indexOf(boardsSelected.elementAt(i)) == -1) {
                boardsSelected.setElementAt(null, i);
            }
        }
    }
    
    public Enumeration<String> getBoardsAvailable() {
        return boardsAvailable.elements();
    }

    public Vector<String> getBoardsAvailableVector() {
        return boardsAvailable;
    }
    
    public void setBoardsAvailableVector(Vector<String> boardsAvailable) {
        this.boardsAvailable = boardsAvailable;
    }
    
    public void setBridgeCF(int cf) {
        if (cf>0) {
            // only set if greater zero. Check for option is done in server.
            for (Enumeration eboards=boardsSelected.elements(); eboards.hasMoreElements(); ) {
                Board board=(Board)eboards.nextElement();
                board.setBridgeCF(cf);
            }
        }
    }
    /**
       Checks, if the Mapgenerator parameters are all valid. If not 
       they are changed to valid values.
    */
    public void validateMapGenParameters() {
    if (hilliness < 0) {
        hilliness = 0;
    }
    if (hilliness > 99) {
        hilliness = 99;
    }
    if (cliffs < 0) {
        cliffs = 0;
    }
    if (cliffs > 100) {
        cliffs = 100;
    }
    if (range < 0 ) {
        range = 0;
    }   
    if (minWaterSpots < 0) {
        minWaterSpots = 0;
    }
    if (maxWaterSpots < minWaterSpots) {
        maxWaterSpots = minWaterSpots;
    }
    if (minWaterSize < 0) {
        minWaterSize = 0;
    }
    if (maxWaterSize < minWaterSize) {
        maxWaterSize = minWaterSize;
    }
    if (probDeep < 0) {
        probDeep = 0;
    }
    if (probDeep > 100) {
        probDeep = 100;
    }
    if (minForestSpots < 0) {
        minForestSpots = 0;
    }
    if (maxForestSpots < minForestSpots) {
        maxForestSpots = minForestSpots;
    }
    if (minForestSize < 0) {
        minForestSize = 0;
    }
    if (maxForestSize < minForestSize) {
        maxForestSize = minForestSize;
    }
    if (probHeavy < 0) {
        probHeavy = 0;
    }
    if (probHeavy > 100) {
        probHeavy = 100;
    }
    if (minRoughSpots < 0) {
        minRoughSpots = 0;
    }
    if (maxRoughSpots < minRoughSpots) {
        maxRoughSpots = minRoughSpots;
    }
    if (minRoughSize < 0) {
        minRoughSize = 0;
    }
    if (maxRoughSize < minRoughSize) {
        maxRoughSize = minRoughSize;
    }
    if (minSwampSpots < 0) {
        minSwampSpots = 0;
    }
    if (maxSwampSpots < minSwampSpots) {
        maxSwampSpots = minSwampSpots;
    }
    if (minSwampSize < 0) {
        minSwampSize = 0;
    }
    if (maxSwampSize < minSwampSize) {
        maxSwampSize = minSwampSize;
    }
    if (minPavementSpots < 0) {
        minPavementSpots = 0;
    }
    if (maxPavementSpots < minPavementSpots) {
        maxPavementSpots = minPavementSpots;
    }
    if (minPavementSize < 0) {
        minPavementSize = 0;
    }
    if (maxPavementSize < minPavementSize) {
        maxPavementSize = minPavementSize;
    }
    if (minRubbleSpots < 0) {
        minRubbleSpots = 0;
    }
    if (maxRubbleSpots < minRubbleSpots) {
        maxRubbleSpots = minRubbleSpots;
    }
    if (minRubbleSize < 0) {
        minRubbleSize = 0;
    }
    if (maxRubbleSize < minRubbleSize) {
        maxRubbleSize = minRubbleSize;
    }
    if (minFortifiedSpots < 0) {
        minFortifiedSpots = 0;
    }
    if (maxFortifiedSpots < minFortifiedSpots) {
        maxFortifiedSpots = minFortifiedSpots;
    }
    if (minFortifiedSize < 0) {
        minFortifiedSize = 0;
    }
    if (maxFortifiedSize < minFortifiedSize) {
        maxFortifiedSize = minFortifiedSize;
    }
    if (minIceSpots < 0) {
        minIceSpots = 0;
    }
    if (maxIceSpots < minIceSpots) {
        maxIceSpots = minIceSpots;
    }
    if (minIceSize < 0) {
        minIceSize = 0;
    }
    if (maxIceSize < minIceSize) {
        maxIceSize = minIceSize;
    }
    if (probRoad < 0) {
        probRoad = 0;
    }
    if (probRoad > 100) {
        probRoad = 100;
    }
    if (probInvert < 0) {
        probInvert = 0;
    }
    if (probInvert > 100) {
        probInvert = 100;
    }
    if (probRiver < 0) {
        probRiver = 0;
    }
    if (probRiver > 100) {
        probRiver = 100;
    }
    if (probCrater < 0) {
        probCrater = 0;
    }
    if (probCrater > 100) {
        probCrater = 100;
    }
    if (minRadius < 0) {
        minRadius = 0;
    }
    if (maxRadius < minRadius) {
        maxRadius = minRadius;
    }
    if (minCraters < 0 ) {
        minCraters = 0;
    }
    if (maxCraters < minCraters) {
        maxCraters = minCraters;
    }
    if (algorithmToUse < 0) {
        algorithmToUse = 0;
    }
    if (algorithmToUse > 2) {
        algorithmToUse = 2;
    }
    } /* validateMapGenParameters */
    
    /**
        Returns true if the this Mapsetting has the same mapgenerator
        settings and size  as the parameter.
        @param other The Mapsetting to which compare.
        @return True if settings are the same.
    */
    public boolean equalMapGenParameters(MapSettings other) {
        if ((this.boardWidth != other.getBoardWidth()) ||
            (this.boardHeight != other.getBoardHeight()) ||
            (this.mapWidth != other.getMapWidth()) ||
            (this.mapHeight != other.getMapHeight()) ||
            (this.invertNegativeTerrain != other.getInvertNegativeTerrain()) ||
            (this.hilliness != other.getHilliness()) ||
            (this.cliffs != other.getCliffs()) ||
            (this.range != other.getRange()) ||
            (this.minWaterSpots != other.getMinWaterSpots()) ||
            (this.maxWaterSpots != other.getMaxWaterSpots()) ||
            (this.minWaterSize != other.getMinWaterSize()) ||
            (this.maxWaterSize != other.getMaxWaterSize()) ||
            (this.probDeep != other.getProbDeep()) ||
            (this.minForestSpots != other.getMinForestSpots()) ||
            (this.maxForestSpots != other.getMaxForestSpots()) ||
            (this.minForestSize != other.getMinForestSize()) ||
            (this.maxForestSize != other.getMaxForestSize()) ||
            (this.probHeavy != other.getProbHeavy()) ||
            (this.minRoughSpots != other.getMinRoughSpots()) ||
            (this.maxRoughSpots != other.getMaxRoughSpots()) ||
            (this.minRoughSize != other.getMinRoughSize()) ||
            (this.maxRoughSize != other.getMaxRoughSize()) ||
            (this.minSwampSpots != other.getMinSwampSpots()) ||
            (this.maxSwampSpots != other.getMaxSwampSpots()) ||
            (this.minSwampSize != other.getMinSwampSize()) ||
            (this.maxSwampSize != other.getMaxSwampSize()) ||
            (this.minPavementSpots != other.getMinPavementSpots()) ||
            (this.maxPavementSpots != other.getMaxPavementSpots()) ||
            (this.minPavementSize != other.getMinPavementSize()) ||
            (this.maxPavementSize != other.getMaxPavementSize()) ||
            (this.minRubbleSpots != other.getMinRubbleSpots()) ||
            (this.maxRubbleSpots != other.getMaxRubbleSpots()) ||
            (this.minRubbleSize != other.getMinRubbleSize()) ||
            (this.maxRubbleSize != other.getMaxRubbleSize()) ||
            (this.minFortifiedSpots != other.getMinFortifiedSpots()) ||
            (this.maxFortifiedSpots != other.getMaxFortifiedSpots()) ||
            (this.minFortifiedSize != other.getMinFortifiedSize()) ||
            (this.maxFortifiedSize != other.getMaxFortifiedSize()) ||
            (this.minIceSpots != other.getMinIceSpots()) ||
            (this.maxIceSpots != other.getMaxIceSpots()) ||
            (this.minIceSize != other.getMinIceSize()) ||
            (this.maxIceSize != other.getMaxIceSize()) ||
            (this.probRoad != other.getProbRoad()) ||
            (this.probInvert != other.getProbInvert()) ||
            (this.probRiver != other.getProbRiver()) ||
            (this.probCrater != other.getProbCrater()) ||
            (this.minRadius != other.getMinRadius()) ||
            (this.maxRadius != other.getMaxRadius()) ||
            (this.minCraters != other.getMinCraters()) ||
            (this.maxCraters != other.getMaxCraters()) ||
            (!this.theme.equals(other.getTheme())) ||
            (this.fxMod != other.getFxMod()) ||
            (this.cityBlocks != other.getCityBlocks()) ||
            (this.cityType != other.getCityType()) ||
            (this.cityMinCF != other.getCityMinCF()) ||
            (this.cityMaxCF != other.getCityMaxCF()) ||
            (this.cityMinFloors != other.getCityMinFloors()) ||
            (this.cityMaxFloors != other.getCityMaxFloors()) ||
            (this.cityDensity != other.getCityDensity()) ||
            (this.probFlood != other.getProbFlood()) ||
            (this.probForestFire != other.getProbForestFire()) ||
            (this.probFreeze != other.getProbFreeze()) ||
            (this.probDrought != other.getProbDrought()) ||
            (this.algorithmToUse != other.getAlgorithmToUse()) ||
            (this.mountainHeightMin != other.getMountainHeightMin()) ||
            (this.mountainHeightMax != other.getMountainHeightMax()) ||
            (this.mountainPeaks != other.getMountainPeaks()) ||
            (this.mountainStyle != other.getMountainStyle()) ||
            (this.mountainWidthMin != other.getMountainWidthMin()) ||
            (this.mountainWidthMax != other.getMountainWidthMax()) ||
            (this.boardBuildings != other.getBoardBuildings())) {
            return false;
        }
		return true;
    } /* equalMapGenParameters */

    /** clone! */
    public Object clone() {
        return new MapSettings(this);
    }

    public int getInvertNegativeTerrain() { return invertNegativeTerrain; }
    public int getHilliness() { return hilliness; }
    public int getCliffs() { return cliffs; }
    public int getRange() { return range; }
    public int getProbInvert() { return probInvert; }
    
    public int getMinWaterSpots() { return minWaterSpots; }
    public int getMaxWaterSpots() { return maxWaterSpots; }
    public int getMinWaterSize() { return minWaterSize; }
    public int getMaxWaterSize() { return maxWaterSize; }
    public int getProbDeep() { return probDeep; }
    
    public int getMinForestSpots() { return minForestSpots; }
    public int getMaxForestSpots() { return maxForestSpots; }
    public int getMinForestSize() { return minForestSize; }
    public int getMaxForestSize() { return maxForestSize; }
    public int getProbHeavy() { return probHeavy; }
    
    public int getMinRoughSpots() { return minRoughSpots; }
    public int getMaxRoughSpots() { return maxRoughSpots; }
    public int getMinRoughSize() { return minRoughSize; }
    public int getMaxRoughSize() { return maxRoughSize; }
    
    public int getMinSwampSpots() { return minSwampSpots; }
    public int getMaxSwampSpots() { return maxSwampSpots; }
    public int getMinSwampSize() { return minSwampSize; }
    public int getMaxSwampSize() { return maxSwampSize; }
    
    public int getMinPavementSpots() { return minPavementSpots; }
    public int getMaxPavementSpots() { return maxPavementSpots; }
    public int getMinPavementSize() { return minPavementSize; }
    public int getMaxPavementSize() { return maxPavementSize; }
    
    public int getMinRubbleSpots() { return minRubbleSpots; }
    public int getMaxRubbleSpots() { return maxRubbleSpots; }
    public int getMinRubbleSize() { return minRubbleSize; }
    public int getMaxRubbleSize() { return maxRubbleSize; }
    
    public int getMinFortifiedSpots() { return minFortifiedSpots; }
    public int getMaxFortifiedSpots() { return maxFortifiedSpots; }
    public int getMinFortifiedSize() { return minFortifiedSize; }
    public int getMaxFortifiedSize() { return maxFortifiedSize; }
    
    public int getMinIceSpots() { return minIceSpots; }
    public int getMaxIceSpots() { return maxIceSpots; }
    public int getMinIceSize() { return minIceSize; }
    public int getMaxIceSize() { return maxIceSize; }
    
    public int getProbRoad() { return probRoad; }
    
    public int getProbRiver() { return probRiver; }
    
    public int getProbCrater() { return probCrater; }
    public int getMinRadius() { return minRadius; }
    public int getMaxRadius() { return maxRadius; }
    public int getMinCraters() { return minCraters; }
    public int getMaxCraters() { return maxCraters; }
    public int getAlgorithmToUse() { return algorithmToUse; }
 
    public int getProbFlood() {return probFlood;}
    public int getProbForestFire() {return probForestFire;}
    public int getProbFreeze() {return probFreeze;}
    public int getProbDrought() {return probDrought;}
    public int getFxMod() {return fxMod;}
    public int getCityBlocks() {return cityBlocks;}
    public String getCityType() {return cityType;}
    public int getCityMinCF() {return cityMinCF;}
    public int getCityMaxCF() {return cityMaxCF;}
    public int getCityMinFloors() {return cityMinFloors;}
    public int getCityMaxFloors() {return cityMaxFloors;}
    public int getCityDensity() {return cityDensity;}
    public int getTownSize() {return townSize;}

    public int getMountainHeightMin() {return mountainHeightMin;}
    public int getMountainHeightMax() {return mountainHeightMax;}
    public int getMountainPeaks() {return mountainPeaks;}
    public int getMountainStyle() {return mountainStyle;}
    public int getMountainWidthMin() {return mountainWidthMin;}
    public int getMountainWidthMax() {return mountainWidthMax;}

    /** set the Parameters for the Map Generator 
    */
    public void setElevationParams(int hill, int newRange, int prob) {
        hilliness = hill;
        range = newRange;
        probInvert = prob;
    }
    
    /** set the Parameters for the Map Generator 
    */
    public void setWaterParams(int minSpots, int maxSpots,
                                int minSize, int maxSize, int prob) {
        minWaterSpots = minSpots;
        maxWaterSpots = maxSpots; 
        minWaterSize = minSize;
        maxWaterSize = maxSize;
        probDeep = prob;
    }
    
    /** set the Parameters for the Map Generator 
    */    
    public void setForestParams(int minSpots, int maxSpots,
                                int minSize, int maxSize, int prob) {
        minForestSpots = minSpots;
        maxForestSpots = maxSpots;
        minForestSize = minSize;
        maxForestSize = maxSize;
        probHeavy = prob;
    }
    
    /** set the Parameters for the Map Generator 
    */
    public void setRoughParams(int minSpots, int maxSpots,
                                int minSize, int maxSize) {
        minRoughSpots = minSpots;
        maxRoughSpots = maxSpots;
        minRoughSize = minSize;
        maxRoughSize = maxSize;
    }
    
    /** set the Parameters for the Map Generator 
     */
     public void setSwampParams(int minSpots, int maxSpots,
                                 int minSize, int maxSize) {
         minSwampSpots = minSpots;
         maxSwampSpots = maxSpots;
         minSwampSize = minSize;
         maxSwampSize = maxSize;
     }
    
    /** set the Parameters for the Map Generator 
     */
     public void setPavementParams(int minSpots, int maxSpots,
                                 int minSize, int maxSize) {
         minPavementSpots = minSpots;
         maxPavementSpots = maxSpots;
         minPavementSize = minSize;
         maxPavementSize = maxSize;
     }
    
     /** set the Parameters for the Map Generator 
      */
      public void setRubbleParams(int minSpots, int maxSpots,
                                  int minSize, int maxSize) {
          minRubbleSpots = minSpots;
          maxRubbleSpots = maxSpots;
          minRubbleSize = minSize;
          maxRubbleSize = maxSize;
      }
     
      /** set the Parameters for the Map Generator 
       */
       public void setFortifiedParams(int minSpots, int maxSpots,
                                   int minSize, int maxSize) {
           minFortifiedSpots = minSpots;
           maxFortifiedSpots = maxSpots;
           minFortifiedSize = minSize;
           maxFortifiedSize = maxSize;
       }
      
     /** set the Parameters for the Map Generator 
      */
      public void setIceParams(int minSpots, int maxSpots,
                                  int minSize, int maxSize) {
          minIceSpots = minSpots;
          maxIceSpots = maxSpots;
          minIceSize = minSize;
          maxIceSize = maxSize;
      }
     
    /** set the Parameters for the Map Generator 
    */
    public void setRiverParam(int prob) { probRiver = prob; }
    
    /** set the Parameters for the Map Generator 
    */
    public void setRoadParam(int prob) { probRoad = prob; }
    
    /** set the Parameters for the Map Generator 
     */
    public void setCliffParam(int prob) { cliffs = prob; }   
    
    /** set the Parameters for the Map Generator 
    */
    public void setCraterParam(int prob, int minCrat,
                                int maxCrat, int minRad, int maxRad) {

        probCrater = prob; 
        maxCraters = maxCrat;
        minCraters=minCrat;
        minRadius = minRad;
        maxRadius = maxRad;
    }
    
    /** set the Parameters for the Map Generator 
     */
    public void setInvertNegativeTerrain(int invert) { invertNegativeTerrain = invert; }
    
    
    /** 
     * set Map generator parameters
     */
    public void setSpecialFX(int modifier, int fire, int freeze, int flood, int drought) {
        fxMod = modifier;
        probForestFire = fire;
        probFreeze = freeze;
        probFlood = flood;
        probDrought = drought;
    }
    public void setAlgorithmToUse(int alg) {
        algorithmToUse = alg;
    }
    
    public void setCityParams( int cityBlocks,
            String cityType,
            int cityMinCF,
            int cityMaxCF,
            int cityMinFloors,
            int cityMaxFloors,
            int cityDensity,
            int townSize) {
        this.cityBlocks = cityBlocks;
        this.cityType = cityType;
        this.cityMinCF = cityMinCF;
        this.cityMaxCF = cityMaxCF;
        this.cityMinFloors = cityMinFloors;
        this.cityMaxFloors = cityMaxFloors;
        this.cityDensity = cityDensity;
        this.townSize = townSize;
    }
    
    public void setMountainParams(
            int mountainPeaks,
            int mountainWidthMin,
            int mountainWidthMax,
            int mountainHeightMin,
            int mountainHeightMax,
            int mountainStyle) {
        this.mountainHeightMax = mountainHeightMax;
        this.mountainHeightMin = mountainHeightMin;
        this.mountainWidthMin = mountainWidthMin;
        this.mountainWidthMax = mountainWidthMax;
        this.mountainPeaks = mountainPeaks;
        this.mountainStyle = mountainStyle;
    }
    
    //note the format is intended to be interoperable with mekwars' existing terrain.xml format
    public void save(OutputStream os) {
        try {
            Writer output = new BufferedWriter( new OutputStreamWriter ( os ) );
            // Output the doctype and header stuff.
            output.write( "<?xml version=\"1.0\"?>" ); //$NON-NLS-1$
            output.write( CommonConstants.NL );
            output.write( "<ENVIRONMENT>" ); //$NON-NLS-1$
            output.write( CommonConstants.NL );

            // theme
            saveParameter( output, "THEME", theme);
            
            // elevation params
            saveParameter( output, "INVERTNEGATIVETERRAIN", invertNegativeTerrain);
            saveParameter( output, "HILLYNESS", hilliness);
            saveParameter( output, "HILLELEVATIONRANGE", range);
            saveParameter( output, "HILLINVERTPROB", probInvert);
            
            saveParameter( output, "ALGORITHM", algorithmToUse);
            saveParameter( output, "CLIFFS", cliffs);
            
            // forest params
            saveParameter( output, "FORESTMINSPOTS", minForestSpots);
            saveParameter( output, "FORESTMAXSPOTS", maxForestSpots);
            saveParameter( output, "FORESTMINHEXES", minForestSize);
            saveParameter( output, "FORESTMAXHEXES", maxForestSize);
            saveParameter( output, "FORESTHEAVYPROB", probHeavy);
            
            // rough params
            saveParameter( output, "ROUGHMINSPOTS", minRoughSpots);
            saveParameter( output, "ROUGHMAXSPOTS", maxRoughSpots);
            saveParameter( output, "ROUGHMINHEXES", minRoughSize);
            saveParameter( output, "ROUGHMAXHEXES", maxRoughSize);
            
            // Swamp params
            saveParameter( output, "SWAMPMINSPOTS", minSwampSpots);
            saveParameter( output, "SWAMPMAXSPOTS", maxSwampSpots);
            saveParameter( output, "SWAMPMINHEXES", minSwampSize);
            saveParameter( output, "SWAMPMAXHEXES", maxSwampSize);
            
            // Road params
            saveParameter( output, "ROADPROB", probRoad);
            
            // water params
            saveParameter( output, "WATERMINSPOTS", minWaterSpots);
            saveParameter( output, "WATERMAXSPOTS", maxWaterSpots);
            saveParameter( output, "WATERMINHEXES", minWaterSize);
            saveParameter( output, "WATERMAXHEXES", maxWaterSize);
            saveParameter( output, "WATERDEEPPROB", probDeep);

            // River params
            saveParameter( output, "RIVERPROB", probRiver);
            
            // Crater params
            saveParameter( output, "CRATERMINNUM", minCraters);
            saveParameter( output, "CRATERMAXNUM", maxCraters);
            saveParameter( output, "CRATERMINRADIUS", minRadius);
            saveParameter( output, "CRATERMAXRADIUS", maxRadius);
            saveParameter( output, "CRATEPROB", probCrater);
            
            // Pavement params
            saveParameter( output, "PAVEMENTMINSPOTS", minPavementSpots);
            saveParameter( output, "PAVEMENTMAXSPOTS", maxPavementSpots);
            saveParameter( output, "PAVEMENTMINHEXES", minPavementSize);
            saveParameter( output, "PAVEMENTMAXHEXES", maxPavementSize);
            
            // Rubble params
            saveParameter( output, "RUBBLEMINSPOTS", minRubbleSpots);
            saveParameter( output, "RUBBLEMAXSPOTS", maxRubbleSpots);
            saveParameter( output, "RUBBLEMINHEXES", minRubbleSize);
            saveParameter( output, "RUBBLEMAXHEXES", maxRubbleSize);

            // Fortified params
            saveParameter( output, "FORTIFIEDMINSPOTS", minFortifiedSpots);
            saveParameter( output, "FORTIFIEDMAXSPOTS", maxFortifiedSpots);
            saveParameter( output, "FORTIFIEDMINHEXES", minFortifiedSize);
            saveParameter( output, "FORTIFIEDMAXHEXES", maxFortifiedSize);

            // Ice params
            saveParameter( output, "ICEMINSPOTS", minIceSpots);
            saveParameter( output, "ICEMAXSPOTS", maxIceSpots);
            saveParameter( output, "ICEMINHEXES", minIceSize);
            saveParameter( output, "ICEMAXHEXES", maxIceSize);
            
            // Special FX
            saveParameter( output, "FXMOD", fxMod);
            saveParameter( output, "PROBFREEZE", probFreeze);
            saveParameter( output, "PROBFLOOD", probFlood);
            saveParameter( output, "PROBFORESTFIRE", probForestFire);
            saveParameter( output, "PROBDROUGHT", probDrought);
            
            // City
            saveParameter( output, "CITYTYPE", cityType);
            saveParameter( output, "CITYBLOCKS", cityBlocks);
            saveParameter( output, "CITYDENSITY", cityDensity);
            saveParameter( output, "MINCF", cityMinCF);
            saveParameter( output, "MAXCF", cityMaxCF);
            saveParameter( output, "MINFLOORS", cityMinFloors);
            saveParameter( output, "MAXFLOORS", cityMaxFloors);
            saveParameter( output, "TOWNSIZE", townSize);
            
            // mountain
            saveParameter( output, "MOUNTPEAKS", mountainPeaks);
            saveParameter( output, "MOUNTWIDTHMIN", mountainWidthMin);
            saveParameter( output, "MOUNTWIDTHMAX", mountainWidthMax);
            saveParameter( output, "MOUNTHEIGHTMIN", mountainHeightMin);
            saveParameter( output, "MOUNTHEIGHTMAX", mountainHeightMax);
            saveParameter( output, "MOUNTSTYLE", mountainStyle);

            // Finish writing.
            output.write( "</ENVIRONMENT>" ); //$NON-NLS-1$
            output.write( CommonConstants.NL );
            output.flush();
            output.close();
        }
        catch(IOException e) {
            
        }
    }
    
    private void saveParameter(Writer output, String name, Object value) throws IOException {
        output.write("    <");
        output.write(name);
        output.write(">");
        output.write(value.toString());
        output.write("</");
        output.write(name);
        output.write(">");
        output.write( CommonConstants.NL );
    }
    
    public void load(InputStream is) {
        ParsedXML root = null;
        try {
            root = TinyParser.parseXML(is);
        } catch (ParseException e) {
            System.err.println("Error parsing map settings xml file.");  //$NON-NLS-1$
            e.printStackTrace();
            return;
        }
        Enumeration rootChildren = root.elements();
        ParsedXML enivoronmentNode = (ParsedXML)rootChildren.nextElement();
        
        if ( enivoronmentNode.getName().equals("ENVIRONMENT") ) { //$NON-NLS-1$
            Enumeration children = enivoronmentNode.elements();
        
            while (children.hasMoreElements()) {
                try {
                    parseEnvironmentNode((ParsedXML)children.nextElement());
                } catch (Exception ex) {
                    System.err.println("error in map settings file:");
                    ex.printStackTrace();
                }
            }
        
        } else {
            System.out.println("Root node of map settings file is incorrectly named. Name should be 'ENVIRONMENT' but name is '" + enivoronmentNode.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
    
    private void parseEnvironmentNode(ParsedXML node) {
        Enumeration values = node.elements();
        if(!(values.hasMoreElements())) {
            return;
        }
        ParsedXML value = (ParsedXML)values.nextElement();
        String param = value.getContent();
        if(null==param)
            return;
        String key = node.getName();
        
        // theme
        if(key.equals("THEME")) theme = param;
        
        // elevation params
        else if(key.equals("INVERTNEGATIVETERRAIN")) invertNegativeTerrain = Integer.valueOf(param);
        else if(key.equals("HILLYNESS")) hilliness = Integer.valueOf(param);
        else if(key.equals("HILLELEVATIONRANGE")) range = Integer.valueOf(param);
        else if(key.equals("HILLINVERTPROB")) probInvert = Integer.valueOf(param);
        
        else if(key.equals("ALGORITHM")) algorithmToUse = Integer.valueOf(param);
        else if(key.equals("CLIFFS")) cliffs = Integer.valueOf(param);
        
        // forest params
        else if(key.equals("FORESTMINSPOTS")) minForestSpots = Integer.valueOf(param);
        else if(key.equals("FORESTMAXSPOTS")) maxForestSpots = Integer.valueOf(param);
        else if(key.equals("FORESTMINHEXES")) minForestSize = Integer.valueOf(param);
        else if(key.equals("FORESTMAXHEXES")) maxForestSize = Integer.valueOf(param);
        else if(key.equals("FORESTHEAVYPROB")) probHeavy = Integer.valueOf(param);
        
        // rough params
        else if(key.equals("ROUGHMINSPOTS")) minRoughSpots = Integer.valueOf(param);
        else if(key.equals("ROUGHMAXSPOTS")) maxRoughSpots = Integer.valueOf(param);
        else if(key.equals("ROUGHMINHEXES")) minRoughSize = Integer.valueOf(param);
        else if(key.equals("ROUGHMAXHEXES")) maxRoughSize = Integer.valueOf(param);
        
        // Swamp params
        else if(key.equals("SWAMPMINSPOTS")) minSwampSpots = Integer.valueOf(param);
        else if(key.equals("SWAMPMAXSPOTS")) maxSwampSpots = Integer.valueOf(param);
        else if(key.equals("SWAMPMINHEXES")) minSwampSize = Integer.valueOf(param);
        else if(key.equals("SWAMPMAXHEXES")) maxSwampSize = Integer.valueOf(param);
        
        // Road params
        else if(key.equals("ROADPROB")) probRoad = Integer.valueOf(param);
        
        // water params
        else if(key.equals("WATERMINSPOTS")) minWaterSpots = Integer.valueOf(param);
        else if(key.equals("WATERMAXSPOTS")) maxWaterSpots = Integer.valueOf(param);
        else if(key.equals("WATERMINHEXES")) minWaterSize = Integer.valueOf(param);
        else if(key.equals("WATERMAXHEXES")) maxWaterSize = Integer.valueOf(param);
        else if(key.equals("WATERDEEPPROB")) probDeep = Integer.valueOf(param);

        // River params
        else if(key.equals("RIVERPROB")) probRiver = Integer.valueOf(param);
        
        // Crater params
        else if(key.equals("CRATERMINNUM")) minCraters = Integer.valueOf(param);
        else if(key.equals("CRATERMAXNUM")) maxCraters = Integer.valueOf(param);
        else if(key.equals("CRATERMINRADIUS")) minRadius = Integer.valueOf(param);
        else if(key.equals("CRATERMAXRADIUS")) maxRadius = Integer.valueOf(param);
        else if(key.equals("CRATEPROB")) probCrater = Integer.valueOf(param);
        
        // Pavement params
        else if(key.equals("PAVEMENTMINSPOTS")) minPavementSpots = Integer.valueOf(param);
        else if(key.equals("PAVEMENTMAXSPOTS")) maxPavementSpots = Integer.valueOf(param);
        else if(key.equals("PAVEMENTMINHEXES")) minPavementSize = Integer.valueOf(param);
        else if(key.equals("PAVEMENTMAXHEXES")) maxPavementSize = Integer.valueOf(param);
        
        // Rubble params
        else if(key.equals("RUBBLEMINSPOTS")) minRubbleSpots = Integer.valueOf(param);
        else if(key.equals("RUBBLEMAXSPOTS")) maxRubbleSpots = Integer.valueOf(param);
        else if(key.equals("RUBBLEMINHEXES")) minRubbleSize = Integer.valueOf(param);
        else if(key.equals("RUBBLEMAXHEXES")) maxRubbleSize = Integer.valueOf(param);

        // Fortified params
        else if(key.equals("FORTIFIEDMINSPOTS")) minFortifiedSpots = Integer.valueOf(param);
        else if(key.equals("FORTIFIEDMAXSPOTS")) maxFortifiedSpots = Integer.valueOf(param);
        else if(key.equals("FORTIFIEDMINHEXES")) minFortifiedSize = Integer.valueOf(param);
        else if(key.equals("FORTIFIEDMAXHEXES")) maxFortifiedSize = Integer.valueOf(param);

        // Ice params
        else if(key.equals("ICEMINSPOTS")) minIceSpots = Integer.valueOf(param);
        else if(key.equals("ICEMAXSPOTS")) maxIceSpots = Integer.valueOf(param);
        else if(key.equals("ICEMINHEXES")) minIceSize = Integer.valueOf(param);
        else if(key.equals("ICEMAXHEXES")) maxIceSize = Integer.valueOf(param);
        
        // Special FX
        else if(key.equals("FXMOD")) fxMod = Integer.valueOf(param);
        else if(key.equals("PROBFREEZE")) probFreeze = Integer.valueOf(param);
        else if(key.equals("PROBFLOOD")) probFlood = Integer.valueOf(param);
        else if(key.equals("PROBFORESTFIRE")) probForestFire = Integer.valueOf(param);
        else if(key.equals("PROBDROUGHT")) probDrought = Integer.valueOf(param);
        
        // City
        else if(key.equals("CITYTYPE")) cityType = param;
        else if(key.equals("CITYBLOCKS")) cityBlocks = Integer.valueOf(param);
        else if(key.equals("CITYDENSITY")) cityDensity = Integer.valueOf(param);
        else if(key.equals("MINCF")) cityMinCF = Integer.valueOf(param);
        else if(key.equals("MAXCF")) cityMaxCF = Integer.valueOf(param);
        else if(key.equals("MINFLOORS")) cityMinFloors = Integer.valueOf(param);
        else if(key.equals("MAXFLOORS")) cityMaxFloors = Integer.valueOf(param);
        else if(key.equals("TOWNSIZE")) townSize = Integer.valueOf(param);
        
        // mountain
        else if(key.equals("MOUNTPEAKS")) mountainPeaks = Integer.valueOf(param);
        else if(key.equals("MOUNTWIDTHMIN")) mountainWidthMin = Integer.valueOf(param);
        else if(key.equals("MOUNTWIDTHMAX")) mountainWidthMax = Integer.valueOf(param);
        else if(key.equals("MOUNTHEIGHTMIN")) mountainHeightMin = Integer.valueOf(param);
        else if(key.equals("MOUNTHEIGHTMAX")) mountainHeightMax = Integer.valueOf(param);
        else if(key.equals("MOUNTSTYLE")) mountainStyle = Integer.valueOf(param);
    }
    
    void loadStringParameter( ParsedXML node, String param, String value) {
        if(node.getName().equals(param)) {
            value = node.getContent();
        }
    }
    
    void loadIntParameter( ParsedXML node, String param, Integer value) {
        if(node.getName().equals(param)) {
            value = Integer.valueOf(node.getContent());
        }
    }
}
