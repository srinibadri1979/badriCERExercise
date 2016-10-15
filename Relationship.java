package curam.custom.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
 

class Relationship {	
	
  //Add a row column value
  private final Table<String, String, List<Child>> relationShipTable = HashBasedTable.create();  
  
	public void addRelationship(String key, String relationShipType, Child child) {

		if (relationShipTable.contains(key, relationShipType)) {

			List<Child> childListFromTable = relationShipTable.get(key,
					relationShipType);
			childListFromTable.add(child);

		} else {

			List<Child> childListForNewRelationShip = new ArrayList<Child>();
			childListForNewRelationShip.add(child);

			relationShipTable.put(key, relationShipType,
					childListForNewRelationShip);

		}

	}  
  
	public void displayRelationShip() {

		Set<String> rowKey = relationShipTable.rowKeySet();		

		for (String string : rowKey) {

			Map<String, List<Child>> map = relationShipTable.row(string);

			// Each map get the Entry set
			for (Map.Entry<String, List<Child>> entry : map.entrySet()) {				

				List<Child> ChildList = entry.getValue();

				for (Child child : ChildList) {

					System.out.println(string + " " + entry.getKey() + " " 
							+ child.getName());

				}

			}

		}

	}
	
	public static void main(String args[]){
		  
		  Relationship relationship = new Relationship();
		  
		  Child child = new Child("SALLY");
		  
		  Child child1 = new Child("MARY");
		  
		  relationship.addRelationship("BOBBY", "IS THE UNCLE OF -> ", child);
		  
		  relationship.addRelationship("BOBBY", "IS THE GRANDPA OF -> ", child1);
		  
		  relationship.displayRelationShip();
		  
		  

	}
}
	  
  class Child{
	  
	  private String name;  
	  
	  Child(String name)
	  {
		  this.name = name;
	  } 
	  
	  public String getName(){
		  return name;
	  }
  }
	  

  
   
  
  
  

