package BungeeSystem.Ban.Manager;

import java.util.ArrayList;
import java.util.List;

public enum MuteUnit
{
  SECOND("Sekunde(n)", 1, "sec"),  MINUTE("Minute(n)", 60, "min"),  HOUR("Stunde(n)", 3600, "hour"),  DAY("Tag(e)", 86400, "day"),  WEEK("Woche(n)", 604800, "week");
  
  private String name;
  private int toSecond;
  private String shortcut;
  
  private MuteUnit(String name, int toSecond, String shortcut)
  {
    this.name = name;
    this.toSecond = toSecond;
    this.shortcut = shortcut;
  }
  
  public int getToSecond()
  {
    return this.toSecond;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getShortcut()
  {
    return this.shortcut;
  }
  
  public static List<String> getUnitsAsString()
  {
    List units = new ArrayList();
    for (MuteUnit unit : values()) {
      units.add(unit.getShortcut().toLowerCase());
    }
    return units;
  }
  
  public static MuteUnit getUnit(String unit)
  {
    for (MuteUnit units : values	()) {
      if (units.getShortcut().toLowerCase().equals(unit.toLowerCase())) {
        return units;
      }
    }
    return null;
  }
}

