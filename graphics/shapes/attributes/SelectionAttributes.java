package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {

	private Boolean selected;
	private String id="selectionattribute";
	
	public String getID()
	{
		return this.id;
	}
	
	public Boolean isSelected()
	{
		return this.selected;
	}
	
	public void select()
	{
		this.selected=true;
	}
	
	public void unselected()
	{
		this.selected=false;
	}
	
	public void toggleSelection()
	{
		this.selected=!this.selected;
	}
	
	public SelectionAttributes()
	{
		this.unselected();
	}
}
